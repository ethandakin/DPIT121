--[=[
	@class RxValueBaseUtils
]=]

local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local RxInstanceUtils = require(SharedPackages.instanceUtils.RxInstanceUtils)
local RxBrioUtils = require(SharedPackages.Brio.Brio)

local RxValueBaseUtils = {}

--[=[
	:::warning
	This caches the last value seen, and may memory leak.
	:::

	@param parent Instance
	@param className string
	@param name string
	@return Observable<any>
	:::
]=]
-- TODO: Handle default value/nothing there, instead of memory leaking!
---@diagnostic disable-next-line: duplicate-set-field
function RxValueBaseUtils.observe(parent, className, name)
	warn("[RxValueBaseUtils.observe] - Deprecated since 4.0.0. Use RxValueBaseUtils.observeBrio")

	return RxInstanceUtils.observeLastNamedChildBrio(parent, className, name)
		:Pipe({
			RxBrioUtils.switchMap(function(valueObject)
				return RxValueBaseUtils.observeValue(valueObject)
			end)
		})
end

--[=[
	Observes a value base underneath a parent (last named child).

	@param parent Instance
	@param className string
	@param name string
	@return Observable<Brio<any>>
]=]
function RxValueBaseUtils.observeBrio(parent, className, name)
	assert(typeof(parent) == "Instance", "Bad parent")
	assert(type(className) == "string", "Bad className")
	assert(type(name) == "string", "Bad naem")

	return RxInstanceUtils.observeLastNamedChildBrio(parent, className, name)
		:Pipe({
			RxBrioUtils.switchMapBrio(function(valueObject)
				return RxValueBaseUtils.observeValue(valueObject)
			end),
			RxBrioUtils.onlyLastBrioSurvives(),
		})
end

--[=[
	Observes a value base underneath a parent

	@param parent Instance
	@param className string
	@param name string
	@param defaultValue any
	@return Observable<any>
]=]
---@diagnostic disable-next-line: duplicate-set-field
function RxValueBaseUtils.observe(parent, className, name, defaultValue)
	assert(typeof(parent) == "Instance", "Bad parent")
	assert(type(className) == "string", "Bad className")
	assert(type(name) == "string", "Bad name")

	return RxValueBaseUtils.observeBrio(parent, className, name)
		:Pipe({
			RxBrioUtils.emitOnDeath(defaultValue)
		})
end


--[=[
	Observables a given value object's value
	@param valueObject Instance
	@return Observable<T>
]=]
function RxValueBaseUtils.observeValue(valueObject)
	return RxInstanceUtils.observeProperty(valueObject, "Value")
end

return RxValueBaseUtils