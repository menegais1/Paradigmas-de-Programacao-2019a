defmodule ListaTelefonica.Router do
  use Plug.Router
  import ListaTelefonica.Data

  plug(:match)
  plug(:dispatch)

  get "/" do
    conn
    |> addCors
    |> put_resp_content_type("application/json")
    |> send_resp(200, Poison.encode!(get()))
  end

  get "/:id" do
    conn
    |> addCors
    |> put_resp_content_type("application/json")
    |> send_resp(200, Poison.encode!(filter_by_id(id)))
  end

  def addCors(conn) do
    conn
    |> put_resp_header("Access-Control-Allow-Origin", "*")
    |> put_resp_header("Access-Control-Allow-Credentials", "*")
    |> put_resp_header("Access-Control-Allow-Headers", "*")
    |> put_resp_header("Access-Control-Allow-Methods", "*")

  end

end
