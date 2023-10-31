local Players = game:GetService("Players")
local ServerStorage = game:GetService("ServerStorage")
local ReplicatedStorage = game:GetService("ReplicatedStorage")
local CollectionService      = game:GetService("CollectionService")

local SharedPackages = ReplicatedStorage.SharedPackages
local ServiceBag = require(SharedPackages.ServiceBag.ServiceBag)

local Classes = ServerStorage.Classes
local Client = require(Classes.Actor.Client)
local CharacterClass = require(Classes.Entity.Character)
local Binder = require(SharedPackages.Binder.Binder)

local testService = ServiceBag.new()
testService:GetService({
    Init = function(...)
        print(...)
    end;
    Start = function(...)
        print(...)
    end;
    Destroy = function(...)
        print(...)
    end
})

testService:Init()

--[[

local PlayerBinder = Binder.new("Client", Client)
PlayerBinder:Start()

local CharacterBinder = Binder.new("Character", function(Character)
    return CharacterClass.new(Players:GetPlayerFromCharacter(Character), Character)
end)
CharacterBinder:Start()


]]

local function CharacterAdded(Character)
    --CollectionService:AddTag(Character, "Character")
    --local CharacterObject = CharacterBinder:Get(Character)
end

local function CharacterRemoving(Character)
    local Player = Players:GetPlayerFromCharacter(Character)

    
end

local function PlayerAdded(Player: Player)
    -- Initialize Client object with PlayerBinder object
    --CollectionService:AddTag(Player, "Client")
    --local ClientObject = PlayerBinder:Get(Player)
    
    --Player.CharacterAdded:Connect(CharacterAdded)
end

local function PlayerRemoving(Player: Player)

end


Players.PlayerAdded:Connect(PlayerAdded)
Players.PlayerRemoving:Connect(PlayerRemoving)

--[[

local Elements = {
    "Air";
    "Earth";
    "Fire";
    "Frost";
    "Lightning";
    "Shadow";
    "Poison";
    "Bleed";
    "Madness";
    "Rot";
    "Light";
    "Sleep";
    "Spirit";
    "Balance";
    "Growth";
}

    local Player = Players:GetPlayerFromCharacter(Character)
    local ElementFolder = Instance.new("Folder")
    ElementFolder.Name = "Elements"
    ElementFolder.Parent = ReplicatedStorage.Live[Player.UserId]

    for i, v in pairs(Elements) do
        local Value = Instance.new("NumberValue")
        Value.Name = v
        Value.Parent = ElementFolder
    end


        local Player = Players:GetPlayerFromCharacter(Character)
    local Data = ReplicatedStorage.Live:FindFirstChild(Player.UserId)
    local ElementFolder = Data:FindFirstChild("Elements")

    ElementFolder:Destroy()

       -- local PlayerFolder = Instance.new("Folder")
    --PlayerFolder.Name = Player.UserId
    --PlayerFolder.Parent = ReplicatedStorage.Live

    
    --Player.CharacterAdded:Connect(CharacterAdded)
    --Player.CharacterRemoving:Connect(CharacterRemoving)\
]]

