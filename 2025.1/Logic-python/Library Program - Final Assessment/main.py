#Importações:
from utils.functions import *
from utils.const_erros import *
# json
from utils.functions import save_data, load_data
from utils import library

#Código:

def menu():
    while True:
        print("=============================================")
        print("🔴 Menu de opções: ")
        print("1 - Cadastrar Livros\n2 - Cadastrar Usuários\n3 - Listar Livros Disponíveis e Emprestados\n4 - Ver usuários \n5 - Realizar Empréstimos \n6 - Remover Empréstimo")
        print("=============================================")
        print("Ou digite \"Sair\" para sair do programa!")
        print("=============================================")
        try:
            option = str(input("Digite a opção: "))
            
            if option == "1":
                try_exception(register_books, ERRO_INESPERADO)
            elif option == "2":
                try_exception(register_users, ERRO_INESPERADO)
            elif option == "3":
                try_exception(list_books, ERRO_INESPERADO)
            elif option == "4":
                try_exception(users_list, ERRO_INESPERADO)
            elif option == "5":
                try_exception(make_loans, ERRO_INESPERADO)
            elif option == "6":
                try_exception(remove_loan, ERRO_INESPERADO)
            elif option.lower() == "sair":
                print("Sistema Encerrado!")
                break
            else:
                print(f"🔴 ERRO: {DIGITE_APENAS_NUMEROS_OU_SAIR}")
        except ValueError:
            print(DIGITE_APENAS_NUMEROS_OU_SAIR)

if __name__ == "__main__":
    # Fazendo o carregamento dos dados ao iniciar
    books, users = load_data()
    library.books_library.update(books)
    library.list_users.update(users)
    menu()