defmodule ListaTelefonica.Data do

  def filter_by_id(id) when is_bitstring(id) do
    id = String.to_integer(id)
    [h|_] = filter(fn %{id: id_param} -> id_param == id end)
    h
  end

  def filter(filter) when is_function(filter) do
    list() |> Enum.filter(filter)
  end

  def get do
    list()
    |> Enum.map(fn %{id: p_id, name: p_name, phone: p_phone} -> %{id: p_id, name: p_name, phone: p_phone} end)
  end

  defp list do
    [
      %{
        id: 1,
        name: "Roberto",
        phone: 53_999_739_562,
        address: "Antonio Borges, 450",
        city: "Porto Alegre",
        state: "Rio Grande do Sul",
      },
      %{
        id: 2,
        name: "Maria",
        phone: 54_980_921_341,
        address: "Padre Hildebrando, 186",
        city: "São Paulo",
        state: "São Paulo",
      },
      %{
        id: 3,
        name: "Carlos",
        phone: 21_331_314_245,
        address: "Alcantra Junior, 1023",
        city: "Rio de Janeiro",
        state: "Rio de Janeiro",
      },
      %{
        id: 4,
        name: "Agostinho",
        phone: 31_232_343_413,
        address: "Carlos Gomes, 456",
        city: "Alto Araguaia",
        state: "Mato Grosso",
      },
      %{
        id: 5,
        name: "João",
        phone: 21_234_312_543,
        address: "João Goulart Filho,1089",
        city: "Ijui",
        state: "Rio Grande do Sul",
      }
    ]
  end
end
