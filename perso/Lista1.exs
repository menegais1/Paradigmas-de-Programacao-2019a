defmodule Lista1 do
  def sumSquares(x,y) do
    x * x + y * y
  end

  def hasEqHeads?([],[]), do: true
  def hasEqHeads?(x, y) do
    [h1|_] = x
    [h2|_] = y
    h1 === h2
  end

  def concatSuper(list) when is_list(list) do
    Enum.map(list, fn x -> x <> "Super" end)
  end

  def numSpaces(str) when is_binary(str) do
    length String.split(str, " ")
  end

  def calcExpression(list) when is_list(list) do
    Enum.map(list,fn x-> 3 * (x *x) + (2 / x) + 1 end)
  end

  def onlyNegatives(list) when is_list(list) do
    Enum.filter(list,fn x-> x < 0 end)
  end

  def between1and100(list) when is_list(list) do
    Enum.filter(list,fn x-> x > 1 and x <= 100 end)
  end

  def after1980(list) when is_list(list) do
    Enum.filter(list,fn x-> 2019 - x > 1980 end)
  end

  def isEven(list) when is_list(list) do
    Enum.filter(list,fn x-> rem(x,2) == 0 end)
  end

  def containChar(str, char) when is_binary(str) and is_binary(char) do
    String.contains?(str, char)
  end

  def endWitha(list) when is_list(list) do
    Enum.filter(list, fn x -> String.ends_with?(x, "a") end)
  end

  def takeWhile(list, func) when is_list(list) and is_fuction(func) do
    [h|t] = list
    if func.(h) do
      [h] ++ takeWhile(t,func)
    else
      []
    end

  end

end


