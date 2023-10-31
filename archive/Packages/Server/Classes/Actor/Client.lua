local Players = game:GetService("Players")
local ServerStorage = game:GetService("ServerStorage")

local Classes = ServerStorage.Classes
local Modules = ServerStorage.Modules
local Data = ServerStorage.Data

local Actor = require(Classes.Actor.Actor)
local ProfileService = require(Modules.ProfileService)

local Client = {}
Client.__index = Client
setmetatable(Client, Actor)

function Client.new(Player: Player)
    local self = Actor.new(Player.UserId)
    self.Player = Player

    setmetatable(self, Client)
    self:_init()

    return self
end

-- called when created
function Client:_init()
    self:CreateLiveInformation()

    -- Profile Service tings brah ???

end

return Client