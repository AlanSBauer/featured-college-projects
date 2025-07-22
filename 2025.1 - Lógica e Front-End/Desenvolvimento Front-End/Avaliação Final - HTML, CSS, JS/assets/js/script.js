const inputTasks = document.getElementById("digitTasks");
const button = document.querySelector("#button");
const tasksPending = document.querySelector(".tasks-pending");
const tasksCompleted = document.querySelector(".tasks-completed");
const tasksPriority = document.querySelector("#tasks-priority");

function colorPriority() {
	if (tasksPriority.value === "high") {
		tasksPriority.style.backgroundColor = "#ff6767";
	} else if (tasksPriority.value === "medium") {
		tasksPriority.style.backgroundColor = "#ffff3a";
	} else if (tasksPriority.value === "low") {
		tasksPriority.style.backgroundColor = "#76ff76";
	} else {
		tasksPriority.style.backgroundColor = "white";
	}
}

tasksPriority.addEventListener("change", colorPriority);

let itemsAdd = [];

button.addEventListener("click", () => {
	const taskText = inputTasks.value.trim().toLowerCase();

	if (taskText === "") {
		alert("Campo vazio!");
		return;
	}
	if (tasksPriority.value === "select") {
		alert("Você não selecionou a prioridade!");
		return;
	}
	if (itemsAdd.includes(taskText)) {
		alert("Tarefa repetida!");
		return;
	}

	const dateCreation = new Date();

	itemsAdd.push({
		text: taskText,
		original: inputTasks.value,
		priority: tasksPriority.value,
		date: dateCreation.getTime(),
	});

	saveTasksToLocalStorage();

	const newDiv = document.createElement("div");
	newDiv.setAttribute("data-name", taskText);
	newDiv.setAttribute("data-priority", tasksPriority.value);
	newDiv.setAttribute("data-date", dateCreation.getTime());

	const text = document.createElement("span");
	text.textContent = inputTasks.value;
	text.style.wordBreak = "break-word";
	newDiv.appendChild(text);

	const completeTask = document.createElement("button");
	completeTask.textContent = "Concluir";

	const deleteTask = document.createElement("button");
	deleteTask.textContent = "Excluir";

	const buttonsRight = document.createElement("div");
	buttonsRight.style.display = "flex";
	buttonsRight.appendChild(completeTask);
	buttonsRight.appendChild(deleteTask);
	newDiv.appendChild(buttonsRight);

	// Estilização da div
	newDiv.style.marginBottom = "10px";
	newDiv.style.borderRadius = "5px";
	newDiv.style.padding = "10px";
	newDiv.style.display = "flex";
	newDiv.style.justifyContent = "space-between";
	newDiv.style.alignItems = "baseline";
	newDiv.style.boxShadow = "1px 1px 1px black";
	completeTask.style.margin = "0 10px";
	completeTask.style.padding = "4px";
	completeTask.style.border = "1px solid black";
	completeTask.style.borderRadius = "20px";
	completeTask.style.backgroundColor = "#28a745";
	completeTask.style.color = "black";
	completeTask.style.fontWeight = "bold";
	completeTask.style.cursor = "pointer";
	completeTask.addEventListener("mouseenter", () => {
		completeTask.style.backgroundColor = "#32ee32	";
	});
	completeTask.addEventListener("mouseleave", () => {
		completeTask.style.backgroundColor = "#28a745";
	});
	deleteTask.style.padding = "4px";
	deleteTask.style.border = "1px solid black";
	deleteTask.style.borderRadius = "20px";
	deleteTask.style.backgroundColor = "#e95353";
	deleteTask.style.color = "black";
	deleteTask.style.fontWeight = "bold";
	deleteTask.style.cursor = "pointer";
	deleteTask.addEventListener("mouseenter", () => {
		deleteTask.style.backgroundColor = "#f82626	";
	});
	deleteTask.addEventListener("mouseleave", () => {
		deleteTask.style.backgroundColor = "#e95353";
	});

	if (tasksPriority.value === "high") {
		newDiv.style.backgroundColor = "#ff6767";
	}
	if (tasksPriority.value === "medium") {
		newDiv.style.backgroundColor = "#ffff3a";
	}
	if (tasksPriority.value === "low") {
		newDiv.style.backgroundColor = "#76ff76";
	}

	// Add div
	tasksPending.appendChild(newDiv);

	inputTasks.value = "";
	tasksPriority.value = "select";
	tasksPriority.style.backgroundColor = "white";
	inputTasks.focus();

	// Options div
	function completedTask() {
		// Movendo
		tasksPending.removeChild(newDiv);
		tasksCompleted.appendChild(newDiv);

		// Removendo button de concluir
		newDiv.removeChild(buttonsRight);

		// Estilo para completas
		newDiv.style.justifyContent = "space-between";

		// Add data de conclusão

		// Poderia fazer assim
		// newDiv.textContent += getDate()
		// Mas para estilizar corretamente:
		const addDate = document.createElement("span");
		addDate.innerHTML = `<b>Concluída:</b> ${getDate()}`;
		addDate.classList.add("completed-date");
		addDate.style.marginLeft = "10px";

		newDiv.appendChild(addDate);

		// // Configurando elementos corretamente
		const divRight = document.createElement("div");

		divRight.appendChild(addDate);
		divRight.appendChild(deleteTask);
		addDate.style.margin = "0 5px";
		divRight.style.display = "flex";
		divRight.style.alignItems = "center";

		newDiv.appendChild(divRight);

		const index = itemsAdd.findIndex((task) => task.text === taskText);
		if (index > -1) {
			itemsAdd[index].completed = true;
			saveTasksToLocalStorage();
		}

		resizeAllTasks();
	}

	function deletedTask() {
		newDiv.remove();
		const index = itemsAdd.findIndex((task) => task.text === taskText);
		if (index > -1) {
			itemsAdd.splice(index, 1);
			saveTasksToLocalStorage();
		}
	}

	completeTask.addEventListener("click", completedTask);
	deleteTask.addEventListener("click", deletedTask);

	resizeAllTasks(); // aplica responsividade ao criar também
});

function getDate() {
	const date = new Date();
	const hour = String(date.getHours()).padStart(2, "0");
	const day = String(date.getDate()).padStart(2, "0");
	const month = String(date.getMonth() + 1).padStart(2, "0");
	const minutes = String(date.getMinutes()).padStart(2, "0");

	return `${day}/${month} - ${hour}:${minutes}`;
}

// LEGENDA MOBILE - MOSTRAR/OCULTAR

const legendMobile = document.querySelector(".legend-mobile");
const legendPriority = document.querySelector(".legend-priority");
const legendText = document.getElementById("legend-text");
legendMobile.addEventListener("click", activeLegend);

function activeLegend() {
	if (legendPriority.style.display === "none") {
		legendPriority.style.display = "flex";
		legendText.textContent = "Ocultar Legenda de Cores";
	} else {
		legendPriority.style.display = "none";
		legendText.textContent = "Mostrar Legenda de Cores";
	}
}

// BARRA DE PESQUISA
const searchInput = document.getElementById("search-input");
const searchMessage = document.querySelector(".search-message");

searchInput.addEventListener("input", () => {
	const searchValue = searchInput.value.toLowerCase();
	const allTasks = document.querySelectorAll(".tasks-pending > div, .tasks-completed > div");
	let tasksFound = false;

	allTasks.forEach((item) => {
		const textSearch = item.textContent.toLowerCase();

		if (textSearch.includes(searchValue)) {
			item.style.display = "flex";
			tasksFound = true;
			item.style.boxShadow = "3px 5px 5px black";
		} else {
			item.style.display = "none";
			item.style.boxShadow = "none";
		}

		if (searchValue === "") {
			item.style.outline = "none";
			item.style.boxShadow = "none";
		}
	});
	if (!tasksFound) {
		searchMessage.textContent = "Nenhuma tarefa encontrada!";
	} else {
		searchMessage.textContent = "";
	}

	if (searchValue === "") {
		searchMessage.textContent = "";
	}
});

// Responsividade
function resizeAllTasks() {
	const isSmall = window.innerWidth <= 465;

	const allTasks = document.querySelectorAll(".tasks-pending div, .tasks-completed div");

	allTasks.forEach((task) => {
		const spans = task.querySelectorAll("span");
		spans.forEach((span) => {
			span.style.fontSize = isSmall ? "12px" : "16px";
		});
	});

	const completedDates = document.querySelectorAll(".completed-date");
	completedDates.forEach((dateSpan) => {
		dateSpan.style.width = isSmall ? "80px" : "100px";
		dateSpan.style.fontSize = isSmall ? "12px" : "16px";
	});
}

window.addEventListener("resize", resizeAllTasks);

//  ------- JSON ----------

function saveTasksToLocalStorage() {
	localStorage.setItem("tasks", JSON.stringify(itemsAdd));
}

function loadTasksFromLocalStorage() {
	const savedData = localStorage.getItem("tasks");
	if (savedData) {
		const parsedTasks = JSON.parse(savedData);
		parsedTasks.forEach((taskObj) => {
			inputTasks.value = taskObj.original;
			tasksPriority.value = taskObj.priority;
			button.click();
		});
	}
}

loadTasksFromLocalStorage();
