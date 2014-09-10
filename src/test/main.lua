    function fact (n)
      if n == 0 then
        return 1
      else
        return n * fact(n-1)
      end
    end

    local a = func(1)
    if a == 0 then
        return 1
    end