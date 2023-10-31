local Players = game:GetService("Players")
local ReplicatedStorage = game:GetService("ReplicatedStorage")
local TweenService = game:GetService("TweenService")

local Player = Players.LocalPlayer

local Shared = ReplicatedStorage:WaitForChild("Shared")
local Client = ReplicatedStorage:WaitForChild("Client")
local Live = ReplicatedStorage:WaitForChild("Live")
local Data = Live:WaitForChild(Player.UserId)
local ElementInfo = require(Shared:WaitForChild("ElementInfo"))

local PlayerGui = Player:WaitForChild("PlayerGui")
local UI = PlayerGui:WaitForChild("UI")
local Afflictions = UI:WaitForChild("Afflictions")

local function GetLayoutOrder()
    local LayoutOrder = 1

    for i, v in pairs(Afflictions:GetChildren()) do
        if v:IsA("Frame") then
            if v.Visible == true then
                LayoutOrder += 1
            end
        end
    end

    return LayoutOrder
end

local function RevealElement(Name: string, Type: boolean)
    if not Afflictions:FindFirstChild(Name) then
        return
    end

    local ElementTweenInfo = {
        ["Icon"] = {
            "BackgroundTransparency";
            "ImageTransparency";
        };
    
        ["Bar"] = {
            "BackgroundTransparency";
        };
    
        ["Slider"] = {
            "BackgroundTransparency";
        };
    
        ["Overlay"] = {
            "ImageTransparency";
        };
    
        ["TextLabel"] = {
            "TextTransparency";
        };
    }

    local Frame = Afflictions:FindFirstChild(Name)
    Frame.LayoutOrder = GetLayoutOrder()

    for i, v in pairs(Afflictions:FindFirstChild(Name):GetDescendants()) do
        if ElementTweenInfo[v.Name] then
            for _, Property in pairs(ElementTweenInfo[v.Name]) do
                TweenService:Create(v, TweenInfo.new(0.125), {[Property] = if Type then 0 else 1}):Play()
            end
        end
    end
end

local function CreateElement(Name: string, Order: number)
    if not ElementInfo[Name] then
        return
    end

    local NewFrame = Client:WaitForChild("ElementTemplate"):Clone()
    NewFrame.LayoutOrder = Order
    NewFrame.Name = Name
    NewFrame.Icon.TextLabel.Text = Name
    NewFrame.Bar.Slider.BackgroundColor3 = ElementInfo[Name].Color
    NewFrame.Visible = false
    NewFrame.LayoutOrder = GetLayoutOrder()
	NewFrame.Parent = Afflictions

    RevealElement(Name, false)
end

local function ElementChanged(Name: string, Value)
    local Frame = Afflictions:FindFirstChild(Name)

    if not Frame then
        return
    end

    local BarTween = TweenService:Create(Frame.Bar.Slider, TweenInfo.new(0.125, Enum.EasingStyle.Quad, Enum.EasingDirection.InOut), {Size = UDim2.new(Value.Value / 100, 0, 0.95, 0)})


    if Value.Value == 0 then
        BarTween:Play()
        RevealElement(Name, false)
        
        task.delay(0.25, function()
            if Value.Value == 0 then
                Frame.Visible = false
            end
        end)

    else
        if Afflictions:FindFirstChild(Name).Visible == false then
            Afflictions:FindFirstChild(Name).Visible = true
            RevealElement(Name, true)
        end

        BarTween:Play()
    end
end

local function CharacterAdded(Character)
	PlayerGui = Player:WaitForChild("PlayerGui")
	UI = PlayerGui:WaitForChild("UI")
	Afflictions = UI:WaitForChild("Afflictions")
	
    local Elements = Data:WaitForChild("Elements")

    for Index, Value in ipairs(Elements:GetChildren()) do
        if not ElementInfo[Value.Name] then
            continue
        end

        CreateElement(Value.Name)

        Value.Changed:Connect(function()
            ElementChanged(Value.Name, Value)
        end)
    end
end

Player.CharacterAdded:Connect(CharacterAdded)
CharacterAdded(Player.Character or Player.CharacterAdded:Wait())