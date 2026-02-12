#=== DICIONÁRIO ===
cadastro_dos_alunos = {"Alan" : 10}

#=== CONSTANTES DE ERRO ===
ERRO_OPCAO_INVALIDA = "Opção inválida, digite um número de 1 - 10."
ERRO_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado!"
ERRO_ALUNO_NAO_ENCONTRADO_PARA_REMOCAO = "Aluno não encontrado para remoção!"
ERRO_NOTA_INVALIDA = "Nota inválida."
ERRO_NOTA_INVALIDA_COM_INTERVALOS = "Nota inválida. A nota deve ser entre 0 e 10."
ERRO_INESPERADO = "Erro inesperado."
ERRO_NENHUM_ALUNO_ENCONTRADO = "Nenhum aluno encontrado no dicionário!"
ERRO_SIMBOLOS = "Você digitou um simbolo"
ERRO_NOME_INVALIDO = "Digite um nome, não apenas simbolos ou números!"
#---------------------------

def menu():
    while True:
        print("========== Menu de Opções: ==========")
        print("1 - Cadastro de Alunos \n2 - Atualizar notas \n3 - Média dos Alunos \n4 - Listar Aprovados \n5 - Remover Aluno")
        print("6 - Verificar Existência de Aluno \n7 - Limpar Dicionário \n8 - Contar Alunos \n9 - Exibir Somente Nomes dos Alunos \n10 - Exibir Todos os Alunos e Notas")
        print("=====================================")
        print("0 - FINALIZAR")
        print("=====================================")
        try:
            opcao = int(input("Digite a opção: "))
            if opcao == 1:
                tentativa(cadastro_alunos, ERRO_NOME_INVALIDO)
            elif opcao == 2:
                tentativa(atualizar_notas, ERRO_NOTA_INVALIDA)
            elif opcao == 3:
                tentativa(media_dos_alunos, ERRO_NENHUM_ALUNO_ENCONTRADO)
            elif opcao == 4:
                tentativa(listar_aprovados, ERRO_INESPERADO)
            elif opcao == 5:
                tentativa(remover_aluno, ERRO_ALUNO_NAO_ENCONTRADO_PARA_REMOCAO)
            elif opcao == 6:
                tentativa(verificar_aluno, ERRO_ALUNO_NAO_ENCONTRADO)
            elif opcao == 7:
                tentativa(limpar_dicionario, ERRO_INESPERADO)
            elif opcao == 8:
                tentativa(contar_alunos, ERRO_INESPERADO)
            elif opcao == 9:
                tentativa(exibir_nomes_alunos, ERRO_INESPERADO)
            elif opcao == 10:
                tentativa(exibir_alunos_e_notas, ERRO_INESPERADO)
            elif opcao == 0:
                print("Sistema Encerrado!")
                break
            else:
                print(f"[ ERRO 🔴 ] -", ERRO_OPCAO_INVALIDA)
        except Exception as erro:
            print(f"[ ERRO 🔴 ] -", ERRO_OPCAO_INVALIDA)
        
def tentativa(funcao, mensagem_erro):
    try:
        funcao()
    except Exception as erro:
        print(f"[ ERRO 🔴 ] - {mensagem_erro}")
        print("=====================================")
        
def validar_nome(nome):
    nome_sem_espaco = nome.replace(" ", "")
    return nome_sem_espaco.isalpha() and len(nome_sem_espaco) > 0
       
def cadastro_alunos():
    while True:
        nome_do_aluno = str(input("Digite o nome do aluno para adicionar, (ou \"n\" para parar): "))
        if nome_do_aluno.lower() == "n":
                break
        if validar_nome(nome_do_aluno):
            nota_do_aluno = int(input("Digite a nota do aluno: "))
            if 0 <= nota_do_aluno <= 10:
                cadastro_dos_alunos[nome_do_aluno] = nota_do_aluno
                print("🔴 Aluno cadastrado!")
            else:
                print("🔴", ERRO_NOTA_INVALIDA_COM_INTERVALOS)
                print("=====================================")
        else:
            raise Exception(ERRO_NOME_INVALIDO)
    
def atualizar_notas():
    nome_do_aluno = str(input("Digite o nome do aluno para alterar a nota: "))
    if validar_nome(nome_do_aluno):
        if nome_do_aluno in cadastro_dos_alunos:
            nova_nota = float(input("Digite a nova nota do aluno: "))
            if 0 <= nova_nota <= 10:
                cadastro_dos_alunos[nome_do_aluno] = nova_nota
                print("🔴 Nota Alterada")
            else:
                print("🔴", ERRO_NOTA_INVALIDA_COM_INTERVALOS)
                print("=====================================")
        else:
            print("🔴",ERRO_ALUNO_NAO_ENCONTRADO)
            print("=====================================")
    else:
        print("🔴", ERRO_NOME_INVALIDO)
        print("=====================================")
    
def media_dos_alunos():
    if cadastro_dos_alunos:
        total_de_notas = 0
        for nota in cadastro_dos_alunos.values():
            total_de_notas += nota
        media = total_de_notas / len(cadastro_dos_alunos)
    
    print(f"🔴 A média geral dos alunos é: {media:.2f}")
    
def listar_aprovados():
    print("🔴 Alunos Aprovados: ")
    for alunos, nota in cadastro_dos_alunos.items():
        if nota >= 6:
            print(alunos)
    
def remover_aluno():
    if not cadastro_dos_alunos:
        print(f"🔴", ERRO_NENHUM_ALUNO_ENCONTRADO)
        return
        
    nome_do_aluno = str(input("Digite o nome do aluno para remove-lo: "))
    if nome_do_aluno in cadastro_dos_alunos: 
        del cadastro_dos_alunos[nome_do_aluno]
        print("🔴 Aluno removido!")
    else:
        raise Exception(ERRO_ALUNO_NAO_ENCONTRADO_PARA_REMOCAO)
    
def verificar_aluno():
    nome_do_aluno = str(input("Digite o nome do aluno para verificar se ele está no dicionário: "))
    if nome_do_aluno in cadastro_dos_alunos:
        print("🔴 O aluno está no dicionário.")
    else:
        raise Exception(ERRO_ALUNO_NAO_ENCONTRADO)
    
def limpar_dicionario():
    print("Tem certeza que deseja apagar o dicionário? ")
    confirmacao = input("Digite ( S / N ): ").lower()
    if confirmacao == "s":
        cadastro_dos_alunos.clear()
        print("🔴 O dicionário foi limpo!")
    else:
        print("🔴 Você cancelou. Voltando ao menu.")
        return
    
def contar_alunos():
    print(f"🔴 Nº de alunos: {len(cadastro_dos_alunos)}")
    
def exibir_nomes_alunos():
    if not cadastro_dos_alunos:
        print("🔴 Nenhum aluno encontrado!")
    idx = 1
    for alunos in cadastro_dos_alunos.keys():
        print(f"{idx} - {alunos}")
        idx += 1
        
def exibir_alunos_e_notas():
    if not cadastro_dos_alunos:
        print("🔴 Nenhum aluno encontrado!")
    idx = 1
    for alunos, notas in cadastro_dos_alunos.items():
        print(f"{idx} - Aluno: {alunos} | Nota: {notas}")
        idx += 1

menu()