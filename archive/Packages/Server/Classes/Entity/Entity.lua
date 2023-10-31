local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local BaseObject = require(SharedPackages.BaseObject.BaseObject)

local Entity = {}
Entity.__index = Entity
setmetatable(Entity, BaseObject)

function Entity.new()
    local self = BaseObject.new()

    return setmetatable(self, Entity)
end

return Entity