using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddOpenApi();

builder.Services.AddDbContext<AppDbContext>(options =>
options.UseSqlite("Data Source=app.db"));

var app = builder.Build();

app.MapGet("/produtos/{id}", (int id, AppDbContext db) =>
{
    var produto = db.Produtos.Find(id);
    if (produto == null)
        return Results.NotFound();
    return Results.Ok(produto);
});

app.MapPost("/produtos", (Produto produto, AppDbContext db) =>
{
    db.Produtos.Add(produto);
    db.SaveChanges();
    return Results.Created();
});

app.MapDelete("/produtos/{id:int}", (int id, AppDbContext db) =>
{
    var produto = db.Produtos.Find(id);
    if (produto == null)
        return Results.NotFound("Produto não encontrado");
    db.Produtos.Remove(produto);
    db.SaveChanges();
    return Results.NoContent();
});

app.MapPut("/produtos/{id:int}", (int id, Produto produtoAtualizado, AppDbContext db) =>
{
    var produto = db.Produtos.Find(id);
    if (produto == null)
        return Results.NotFound("Produto não encontrado");

    produto.Nome = produtoAtualizado.Nome;
    produto.Preco = produtoAtualizado.Preco;

    db.SaveChanges();
    return Results.Ok(produto);

});




if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}

app.UseHttpsRedirection();

app.Run();