# Importações:
from utils.const_erros import *
from utils.functions import *

#Código
def menu():
    while True:
        print("========================================")
        print("🔴 Menu de opções: ")
        print("========================================")
        print("1 - Cronômetro (Contagem progressiva)\n2 - Conversor de Temperatura\n3 - Sorteio de Números\n4 - Relógio Digital\n5 - Temporizador\n6 - Operações Matemáticas")
        print("========================================")
        print("0 - Encerrar")
        print("========================================")
        try:
            option = int(input("Digite a opção desejada: "))  
            if option == 1:
                try_exception(cronometro, DIGITE_UM_TEMPO_EM_SEGUNDOS)
            elif option == 2:
                try_exception(conversor_temperatura, DIGITE_UM_NUMERO_VALIDO_1_5)
            elif option == 3:
                try_exception(sorteio_numeros, DIGITE_NUMEROS_INTEIROS)
            elif option == 4:
                try_exception(relogio_digital, ERRO_INESPERADO)
            elif option == 5:
                try_exception(temporizador, DIGITE_UM_TEMPO_EM_SEGUNDOS)
            elif option == 6:
                try_exception(operacoes_matematicas, DIGITE_UM_NUMERO_VALIDO_1_5)
            elif option == 0:
                print("Sistema Encerrado!")
                break
            else:
                print(f"🔴 ERRO: {DIGITE_UM_NUMERO_VALIDO}")
        except:
            print(f"🔴 ERRO: {DIGITE_UM_NUMERO_VALIDO}")
            
menu()
            