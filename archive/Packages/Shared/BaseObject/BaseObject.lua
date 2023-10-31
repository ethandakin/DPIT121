local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local Maid = require(SharedPackages.Maid.Maid)

local BaseObject = {}
BaseObject.ClassName = "BaseObject"
BaseObject.__index = BaseObject

function BaseObject.new(obj: Instance?)
	local self = setmetatable({}, BaseObject)

	self._maid = Maid.new()
	self._obj = obj

	return self
end

function BaseObject:Destroy()
	self._maid:DoCleaning()
	setmetatable(self, nil)
end

return BaseObject