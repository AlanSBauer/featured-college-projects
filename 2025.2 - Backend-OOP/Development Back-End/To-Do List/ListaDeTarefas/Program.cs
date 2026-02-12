var builder = WebApplication.CreateBuilder(args);

builder.Services.AddOpenApi();

var app = builder.Build();

var tarefas = new List<Tarefa>();
var proximoId = 1;

//Get /tarefas = Lista todas as tarefas

app.MapGet("/tarefas", () =>
{
    return Results.Ok(tarefas);
});

//Get /tarefas/{Id} - Buscar tarefa por Id

app.MapGet("/tarefas/{id:int}", (int id) =>
{
    Tarefa tarefaEncontrada = null;
    foreach (var tarefa in tarefas)
    {
        if (tarefa.Id == id)
        {
            tarefaEncontrada = tarefa;
            return Results.Ok(tarefaEncontrada);
        }
    }
    return Results.NotFound($"tarefa com id {id} não encontrado");
});

//put /tarefas/id - atualizar tarefa existente
app.MapPut("/tarefas/{id:int}", (int id, Tarefa tarefaAtualizada) =>
{
    var tarefaExistente = tarefas.FirstOrDefault(tarefa => tarefa.Id == id);
    if (tarefaExistente == null)
        return Results.NotFound($"tarefa com id {id} não encontrado");
    tarefaExistente.Titulo = tarefaAtualizada.Titulo;
    tarefaExistente.Descricao = tarefaAtualizada.Descricao;
    tarefaExistente.Concluida = tarefaAtualizada.Concluida;
    return Results.Ok(tarefaExistente);
});

//Post /tarefas criar tarefas
app.MapPost("/tarefas", (Tarefa novaTarefa) =>
{
    //Validação Básica
    if (string.IsNullOrEmpty(novaTarefa.Titulo))
        return Results.BadRequest($"O titulo é obrigatório");
    // Configurar propriedades automáticas
    novaTarefa.Id = proximoId++;
    novaTarefa.DataCriacao = DateTime.Now;
    novaTarefa.Concluida = false;
    tarefas.Add(novaTarefa);
    return Results.Created($"/tarefas/{novaTarefa.Id}", novaTarefa);
});

app.MapDelete("/tarefas/{id:int}", (int id) =>
{
    var tarefa = tarefas.FirstOrDefault(t => t.Id == id);
    if (tarefa== null)
        return Results.NotFound($"Tarefa com id {id} não encotrada!");
    tarefas.Remove(tarefa);
    return Results.NoContent();
});

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}

app.UseHttpsRedirection();

app.Run();