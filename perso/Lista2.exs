defmodule Lista2 do
  def isVowel?(a) when is_binary(a) do
    case a do
      "a" -> true
      "e" -> true
      "i" -> true
      "o" -> true
      "u" -> true
      _ -> false
    end
  end

  def isVowel1?(a) do
    case a do
      ?a -> true
      ?e -> true
      ?i -> true
      ?o -> true
      ?u -> true
      _ -> false
    end
  end

  def addComma(list) when is_list(list) do
    Enum.map(list, fn x -> x <> "," end)
  end

  def htmlList(list) when is_list(list) do
    Enum.map(list, fn x -> "<li>" <> x <> "</li>" end)
  end

  def htmlListAux(str) when is_binary(str) do
    "<li>" <> str <> "</li>"
  end

  def htmlList1(list) when is_list(list) do
    Enum.map(list, &htmlListAux/1)
  end

  def removeVowel(str) when is_binary(str) do
    String.replace(str, "a", "")
    |> String.replace("e", "")
    |> String.replace("i", "")
    |> String.replace("o", "")
    |> String.replace("u", "")
  end

  def removeVowel1(str) when is_binary(str) do
    String.to_charlist(str) |> Enum.filter(fn x -> not isVowel1?(x) end) |> List.to_string()
  end

  def removeVowel2(str) when is_binary(str) do
    String.replace(str, ~r/[aeiouAEIOU]/, "")
  end

  def cripto(str) when is_binary(str) do
    String.replace(str, ~r/[a-zA-z0-9]/, "-")
  end

  def firstName(str) when is_binary(str) do
    String.to_charlist(str) |> Enum.take_while(fn x -> x != ?\s end) |> List.to_string()
  end

  def firstName1(str) when is_binary(str) do
    [h | _] = String.split(str, " ")
    h
  end

  def isInt?(str) when is_binary(str) do
    not Regex.match?(~r/[^0-9]/, str)
  end

  def lastName1(str) when is_binary(str) do
    String.split(str, " ") |> Enum.reverse() |> Enum.at(0)
  end

  def userName(str) when is_binary(str) do
    (String.at(str, 0) <> lastName1(str)) |> String.downcase()
  end

  def encodeName(str) when is_binary(str) do
    String.replace(str, ~r/a/i, "4")
    |> String.replace(~r/e/i, "3")
    |> String.replace(~r/i/i, "2")
    |> String.replace(~r/o/i, "1")
    |> String.replace(~r/u/i, "0")
  end

  def betterEncodeName(str) when is_binary(str) do
    String.replace(str, ~r/a/i, "4")
    |> String.replace(~r/e/i, "3")
    |> String.replace(~r/i/i, "1")
    |> String.replace(~r/o/i, "0")
    |> String.replace(~r/u/i, "00")
  end

  def truncateStrings(list) when is_list(list) do
    Enum.map(list, fn x ->
      if String.length(x) > 10 do
        String.slice(x, 0, 10)
      else
        (x <> "..........") |> String.slice(0, 10)
      end
    end)
  end
end
