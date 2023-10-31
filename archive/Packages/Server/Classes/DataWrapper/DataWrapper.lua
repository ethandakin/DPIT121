local ReplicatedStorage = game:GetService("ReplicatedStorage")
local SharedPackages = ReplicatedStorage.SharedPackages

local BaseObject = require(SharedPackages.BaseObject.BaseObject)

local DataWrapper = {}
DataWrapper.__index = DataWrapper
setmetatable(DataWrapper, BaseObject)

local Types = {
	["string"] = "StringValue",
	["boolean"] = "BoolValue",
	["number"] = "NumberValue"
}

function DataWrapper.new()
    local self = BaseObject.new()

    return setmetatable(self, DataWrapper)
end

function DataWrapper:SetData(Data)
	self.Data = Data
end

function DataWrapper:DeepCopyTable(Base)
    local Copy = {}

    for key, value in pairs(Base) do
		if type(value) == "table" then
			Copy[key] = self:DeepCopyTable(value)
		else
			Copy[key] = value
		end
	end

	return Copy
end

function DataWrapper:Reconcile(Target, Template)
    for key, value in pairs(Template) do
		if type(key) == "string" then
			if Target[key] == nil then
				if type(value) == "table" then
					Target[key] = self:DeepCopyTable(value)
				else
					Target[key] = value
				end
			elseif type(Target[key]) == "table" and type(value) == "table" then
				self:Reconcile(Target[key], value)
			end
		end
	end
end

function DataWrapper:ConstructData(Data, Parent)
	for key, value in pairs(Data) do
		if type(value) == "table" then
			local Folder = Instance.new("Folder")
			Folder.Name = key
			Folder.Parent = Parent

			self:ConstructData(value, Folder)
		else
			local Value = Instance.new(Types[type(value)])
			Value.Name = key
			Value.Value = value
			Value.Parent = Parent
					
			Value.Changed:Connect(function(newvalue)
				DataWrapper:Changed(key, Data[key], newvalue)
				Data[key] = newvalue
			end)
		end
	end
end

function DataWrapper:Changed(Index, OldValue, NewValue)
	print(Index, OldValue, NewValue)
end

return DataWrapper