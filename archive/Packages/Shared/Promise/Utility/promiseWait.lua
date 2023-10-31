--[=[
	Wraps the task.delay() API in a promise

	@class promiseWait
]=]

local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local Promise = require(SharedPackages.Promise.Promise)

return function(time)
	return Promise.new(function(resolve, _)
		task.delay(time, function()
			resolve()
		end)
	end)
end