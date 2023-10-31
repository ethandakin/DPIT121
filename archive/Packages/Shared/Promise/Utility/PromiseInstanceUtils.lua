--[=[
	@class PromiseInstanceUtils
]=]

local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local Promise = require(SharedPackages.Promise.Promise)
local Maid = require(SharedPackages.Maid.Maid)

local PromiseInstanceUtils = {}

--[=[
	Promise that resolves when an instance is removed

	@param instance Instance
	@return Promise
]=]
function PromiseInstanceUtils.promiseRemoved(instance)
	assert(instance:IsDescendantOf(game))

	local maid = Maid.new()
	local promise = Promise.new()

	maid:GiveTask(instance.AncestryChanged:Connect(function(_, parent)
		if not parent then
			promise:Resolve()
		end
	end))

	promise:Finally(function()
		maid:DoCleaning()
	end)

	return promise
end

return PromiseInstanceUtils