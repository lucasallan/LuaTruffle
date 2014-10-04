function fibonacci(n)
  if n < 2 then
    return n
  end
  return fibonacci(n-1) + fibonacci(n-2)
end

while true do
  local start = os.clock()
  print(fibonacci(40))
  print(os.clock() - start)
end
