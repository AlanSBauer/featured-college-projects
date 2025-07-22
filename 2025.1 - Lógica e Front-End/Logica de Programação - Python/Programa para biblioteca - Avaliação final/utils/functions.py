# Imports:
from utils.library import books_library, list_users
#importando biblioteca para verificar nomes em python:
import re
# Import json para salvar dados
import json
import os # Importando para solucionar erro, caso os arquivos do json estiverem vazios

def load_data():
    try:
        if os.path.getsize("data/books.json") > 0:
            with open("data/books.json", "r", encoding="utf-8") as f:
                books = json.load(f)
        else:
            books = {"available": {}, "borrowed": {}}
    except FileNotFoundError:
        books = {"available": {}, "borrowed": {}}

    try:
        if os.path.getsize("data/users.json") > 0:
            with open("data/users.json", "r", encoding="utf-8") as f:
                users = json.load(f)
        else:
            users = {}
    except FileNotFoundError:
        users = {}

    return books, users

# Funções
# Salvar dados
def save_data(books_library, list_users):
    with open("data/books.json", "w", encoding="utf-8") as f:
        json.dump(books_library, f, indent=4, ensure_ascii=False)

    with open("data/users.json", "w", encoding="utf-8") as f:
        json.dump(list_users, f, indent=4, ensure_ascii=False)

# Carregar dados

def load_data():
    try:
        if os.path.getsize("data/books.json") > 0:
            with open("data/books.json", "r", encoding="utf-8") as f:
                books = json.load(f)
        else:
            books = {"available": {}, "borrowed": {}}
    except FileNotFoundError:
        books = {"available": {}, "borrowed": {}}

    try:
        if os.path.getsize("data/users.json") > 0:
            with open("data/users.json", "r", encoding="utf-8") as f:
                users = json.load(f)
        else:
            users = {}
    except FileNotFoundError:
        users = {}

    return books, users


# Verificar nome com biblioteca
def verify(name):
    name = name.strip()
    return re.fullmatch(r"[A-Za-zÀ-ÖØ-öø-ÿ\.]+(?: [A-Za-zÀ-ÖØ-öø-ÿ\.]+)*", name) is not None

# Função para try / remover erros
def try_exception(function, mensagem_erro):
    try:
        function()
    except Exception as erro:
        print(f"🔴 ERRO: {mensagem_erro}")
        
           
# Registrar livros
def register_books():
    print("Para realizar o cancelamento do registro, digite \"Cancelar\"!")
    print("=============================================")
    while True:
        title_book = str(input(("Título do livro: "))).strip()
        if title_book.lower() == "cancelar":
            return
        elif not title_book.isalpha():
            print("🔴 O nome do livro não pode conter simbolos!")
            continue
        else: 
            break
        
    while True:
        author_book = str(input("Autor do livro: ")).strip()
        if not verify(author_book):
            print("🔴 Nome inválido! Use apenas letras e espaços, sem símbolos ou números.")    
        elif author_book.lower() == "cancelar":
            return
        elif author_book.isnumeric():
            print("🔴 O nome do autor(a), não pode ser números!")
        else:
            break
        
    while True:
        try:
            year_book = int(input("Ano de publicação do livro: "))
            if len(str(year_book)) != 4:
                print("🔴 É necessario colocar as 4 letras do ano!")
            else:
                break
        except ValueError:
            print("🔴 O ano tem que ser digitado com números inteiros!")
    
    new_book = {"author": author_book, "year": year_book}
    books_library["available"][title_book] = new_book

    save_data(books_library, list_users)
    print("✅ Livro cadastrado com sucesso!")
 
 
# Registrar usuários
def register_users():
    print("Para realizar o cancelamento do registro, digite \"Cancelar\"!")
    print("=============================================")
    
    while True:
        name_user = str(input("Informe o nome do usuário: ")).strip()
        if not verify(name_user):
            print("🔴 Nome inválido! Use apenas letras e espaços, sem símbolos ou números.")  
        elif name_user.isnumeric():
            print("🔴 O nome não pode conter número!")
        elif name_user.lower() == "cancelar":
            return
        else:
            break
        
    while True:
        cpf_user = str(input("Informe o CPF do usuário (somente números ou formatado): ")).strip()
           
        if cpf_user.lower() == "cancelar":
            return

        cpf_limpo = clean_cpf(cpf_user)

        if len(cpf_limpo) != 11:
            print("🔴 CPF inválido! Deve conter 11 dígitos numéricos.")
            continue

        break
    
    list_users[name_user] = {"CPF": cpf_limpo}
    
    save_data(books_library, list_users)
    print("✅ Usuário cadatrado com sucesso!")

# Listar usuários
def users_list():
    if not list_users:
        print("🔴 Nenhum usuário cadastrado!")
        return
    for user in list_users:
        cpf = list_users[user]["CPF"]
        cpf_format = format_cpf(cpf)
        print(f"Nome: {user} - CPF: {cpf_format}")
        

# Listar livros
def list_books():
    print("=============================================")
    print("1 - Livros disponíveis\n2 - Livros emprestados")
    option = input("Digite a opção: ").strip()
    
    if option == "1":
        if not books_library["available"]:
            print("🔴 Não há livros disponíveis!")
            return
        print("=============================================")
        print("LIVROS DISPONÍVEIS: ")
        for title in books_library["available"]:
            book_infos = books_library["available"][title]
            author = book_infos["author"]
            year = book_infos["year"]
            print(f"Título: {title} | Autor: {author} - Ano: {year}")
                
    elif option == "2":
        if not books_library["borrowed"]:
            print("🔴 Não há livros emprestados!")
            return
        print("=============================================")
        print("LIVROS EMPRESTADOS: ")
        for title in books_library["borrowed"]:
            book_infos = books_library["borrowed"][title]
            name_user = book_infos["Name"]
            cpf = book_infos["CPF"]
            
            print(f"LIVRO: {title} || Emprestado para: {name_user} - CPF: {cpf}")
    
    else:
        print("🔴 Opção inválida.")
         
            
# Adicionar empréstimo
def make_loans():
    print("Para realizar o cancelamento do empréstimo, digite \"Cancelar\"!")
    print("=============================================")
    
    name_user = str(input("Nome do usuário: ")).strip()
    
    for books in books_library["borrowed"]:
        if books_library["borrowed"][books]["Name"] == name_user:
            print("🔴 O usuário ja está com um livro, ao devolver pode pegar outro!")
            return
    
    if name_user.lower() == "cancelar":
        return
    
    if name_user not in list_users:
        print("🔴 Usuário não encontrado!")
        print("⚠️ - Erro ao digitar ou cadastro inexistente!")
        return
    
    book = str(input("Título do livro: ")).strip()
    
    if book.lower() == "cancelar":
        return
    
    # Verificar se já não está com a mesma pessoa:
    if book in books_library["borrowed"]:
        if books_library["borrowed"][book]["Name"] == name_user:
            print("🔴 O livro já está com este usuário!")
            return
        else:
            print("🔴 O Livro foi emprestado a outro usuário!")
            return
    # Verificar se tem o livro disponível na biblioteca:
    if book not in books_library["available"]:
        print("🔴 Livro não disponivel na biblioteca!")
        return
    else:
        book_author = books_library["available"][book]["author"] 
        book_year = books_library["available"][book]["year"]
        del books_library["available"][book]
        cpf = list_users[name_user]["CPF"]
        books_library["borrowed"][book] = {"Name": name_user, "CPF": cpf, "author": book_author, "year": book_year}
        
        save_data(books_library, list_users)
        print("=============================================")
        print(f"O livro {book} foi emprestado para {name_user}!")
        print("✅ Empréstimo realizado com sucesso!")
        print("=============================================")
        
# Remover empréstimo
def remove_loan():
    print("Para realizar o cancelamento da remoção do empréstimo, digite \"Cancelar\"!")
    print("=============================================")
    
    # Não coloquei os ifs para conferir se o usuario esta na list_users, pois se o usuário ja fez o empréstimo, é porque ja está cadastrado...
    
    name_user = str(input("Nome do usuário: ")) .strip()
    if name_user not in list_users:
        print("🔴 O usuário não foi encontrado!")
        return
    
    if name_user.lower() == "cancelar":
        return
    
    book = str(input("Título do livro: ")).strip()
    
    if book.lower() == "cancelar":
        return
    
    if book not in books_library["borrowed"]:
        print("🔴 O livro não foi emprestado para ninguém!")
        return
    
    book_infos = books_library["borrowed"][book]
    book_author = book_infos["author"]
    book_year = book_infos["year"]
    
    del books_library["borrowed"][book]
    books_library["available"][book] = {"author": book_author, "year": book_year}
    
    save_data(books_library, list_users)
    print("=============================================")
    print(f"O usuário {name_user} não está mais com o livro {book}!")    
    print("✅ Empréstimo removido com sucesso!")
    print("=============================================")
    
    
# Formatar o cpf para ficar top
def format_cpf(cpf_str):
    return f"{cpf_str[:3]}.{cpf_str[3:6]}.{cpf_str[6:9]}-{cpf_str[9:]}"

# Mesmo que a pessoa digite as pontuaçoes do cpf, o input não vai dar erro por isso!
def clean_cpf(cpf):
    return ''.join(filter(str.isdigit, cpf))