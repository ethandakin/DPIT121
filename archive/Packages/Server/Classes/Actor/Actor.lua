local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local BaseObject = require(SharedPackages.BaseObject.BaseObject)

local Actor = {}
Actor.__index = Actor
setmetatable(Actor, BaseObject)

function Actor.new(ID: number)
    local self = BaseObject.new()
    self.ID = ID

    return setmetatable(self, Actor)
end

function Actor:ChangeID(ID: number)
    self.ID = ID
    if self.Live then
        self.Live.Name = ID
    end
end

function Actor:CreateLiveInformation()
    assert(self.ID, "Actor does not have ID")

    local Live = Instance.new("Folder")
    Live.Name = self.ID
    Live.Parent = ReplicatedStorage.Live
end

return Actor