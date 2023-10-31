--[[
	@class RxInstanceUtils.spec.lua
]]

local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local RxInstanceUtils = require(SharedPackages.instanceUtils.RxInstanceUtils)

return function()
	describe("RxInstanceUtils.observeChildrenBrio", function()
		local part = Instance.new("Part")
		local observe = RxInstanceUtils.observeChildrenBrio(part)
		local externalResult = nil

		it("should not emit anything", function()
			observe:Subscribe(function(result)
				externalResult = result
			end)

			expect(externalResult).to.equal(nil)
		end)
	end)
end
