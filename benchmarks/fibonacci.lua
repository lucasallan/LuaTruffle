function fibonacci(n)
  if n < 2 then
    return n
  end
  return fibonacci(n-1) + fibonacci(n-2)
end

print("Starting benchmark")
-- warming the JVM
local i = 1
while i < 10 do
  fibonacci(40)
  local i = i + 1
end

while true do
  local start = os.clock()
  -- print(fibonacci(40))
  fibonacci(40)
  print(os.clock() - start)
end
