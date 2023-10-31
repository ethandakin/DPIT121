local Players = game:GetService("Players")
local RunService = game:GetService("RunService")
local ServerStorage = game:GetService("ServerStorage")
local ReplicatedStorage = game:GetService("ReplicatedStorage")

local SharedPackages = ReplicatedStorage.SharedPackages
local Classes = ServerStorage.Classes
local Modules = ServerStorage.Modules
local Data = ServerStorage.Data

local Entity = require(Classes.Entity.Entity)
local ProfileService = require(Modules.ProfileService)
local DataWrapper = require(Classes.DataWrapper.DataWrapper)
local BaseData = require(Data.Profiles.Entity)

local Character = {}
Character.__index = Character
setmetatable(Character, Entity)

function Character.new(Player: Player, CharacterInstance)
    local self = Entity.new()
    self.Player = Player
    print(Player)
    self.Character = CharacterInstance
    self.Connections = {}

    setmetatable(self, Character)
    self:_init()

    return self
end

-- called when created
function Character:_init()
    self:InitializeData()
end

function Character:InitializeData()
    self.Player = Players:GetPlayerFromCharacter(self.Character)
    self.Data = DataWrapper.new()
    self.Data:SetData(self.Data:DeepCopyTable(BaseData))
    self.Data:ConstructData(self.Data.Data, ReplicatedStorage.Live:FindFirstChild(self.Player.UserId))
    self:HealthRegen()
    --print(self)
end

function Character:HealthRegen()
    self.Character.Humanoid.MaxHealth = self.Data.Statistics.Max

    local Counter = 0

    self.Connections["HealthRegen"] = RunService.Heartbeat:Connect(function(deltaTime)
        Counter += deltaTime

        if Counter >= self.Data.Statistics.Health.Step then
            Counter -= self.Data.Statistics.Health.Step

            self.Character.Humanoid.Health = self.Character.Humanoid.Health + self.Data.Statistics.Health.Rate
        end
    end)
end

return Character