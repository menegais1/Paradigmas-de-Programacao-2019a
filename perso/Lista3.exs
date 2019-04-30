defmodule Lista3 do
  def add10toall(list) when is_list(list) do
    for n <- list, do: n + 10
  end

  def multN(num, list) when is_integer(num) and is_list(list) do
    for x <- list, do: x * num
  end

  def applyExpr(list) when is_list(list) do
    for x <- list, do: 3 * x + 2
  end

  def addSuffix(str, strList) when is_bitstring(str) and is_list(strList) do
    for x <- strList, do: x <> str
  end

  def selectgt5(list) when is_list(list) do
    for x <- list, x > 5, do: x
  end

  def sumOdds(list) when is_list(list) do
    for(x <- list, rem(x, 2) != 0, do: x) |> Enum.sum()
  end

  def selectExpr(list) when is_list(list) do
    for x <- list, rem(x, 2) == 0 and x > 20 and x < 50, do: x
  end

  def countShorts(list) when is_list(list) do
    for(x <- list, String.length(x) < 5, do: x) |> length()
  end

  def calcExpr(list) when is_list(list) do
    for(x <- list, do: x * x / 2) |> Enum.filter(fn c -> c > 10 end)
  end

  def trSpaces(str) when is_bitstring(str) do
    String.replace(str, " ", "-")
  end

  def selectSnd(list) when is_list(list) do
    for {_, y} <- list, do: y
  end

  def dotProd(list1, list2) when is_list(list1) and is_list(list2) do
    list = Enum.zip(list1, list2)
    for({x, y} <- list, do: x * y) |> Enum.sum()
  end

  def genRects(num, {0, 0}) when is_number(num) do
    height = 5.5
    width = 5.5
    for n <- 0..(num - 1), do: {n * width, 0, width, height}
  end

  def genRects(num, {x, y}) when is_number(num) do
    height = 5
    width = 5
    for n <- 1..num, do: {x + n * width, y, width, height}
  end

  def myZipWith(list1, list2, func, resultFunc)
      when is_list(list1) and
             is_list(list2) and
             is_function(func) and
             is_function(resultFunc) do
    list = Enum.zip(list1, list2)
    for({x, y} <- list, do: func.(x, y)) |> resultFunc.()
  end
end
