let contadorTarefas = 0;

function adicionarTarefa() {
  const input = document.getElementById("inputTarefa");

  const texto = input.value.trim();

  if(texto === "") {
    return alert('Digite uma tarefa antes de adicionar!')
  }

  const lista = document.getElementById("lista")

  const item = document.createElement("li")

  item.innerHTML = 
    `<span class="textoTarefa">${texto}</span>
    <div>
        <button onclick="concluirTarefa(this)">✔ Concluir</button>
        <button onclick="removerTarefa(this)">✖ Remover</button>
    </div>`;

  lista.appendChild(item);

  input.value = "";
  input.focus();

  contadorTarefas += 1;
  atualizarContador();

}

function removerTarefa(botao) {
  const item = botao.parentElement.parentElement;

  item.remove();

  contadorTarefas -= 1
  atualizarContador();

}

function concluirTarefa(botao) {
  const item = botao.parentElement.parentElement;
  const span = item.querySelector(".textoTarefa");

  span.classList.toggle("concluido")

}

function atualizarContador() {
  const contadorElemento = document.getElementById("contadorTexto")

  if(contadorTarefas === 0) {
    contadorElemento.innerHTML = "Nenhuma tarefa adicionada."
  } else if (contadorTarefas === 1) {
    contadorElemento.innerHTML = "1 tarefa na lista."
  } else {
    contadorElemento.innerHTML = `${contadorTarefas} tarefas na lista.`
  }
}