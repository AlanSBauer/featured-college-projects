# Importando Cconstante de erro   
import time
import random
from datetime import datetime
from utils.const_erros import DIGITE_UM_NUMERO_VALIDO_1_5

# Função TRY
def try_exception(function, mensagem_erro):
    try:
        function()
    except Exception as erro:
        print("========================================")
        print(f"🔴 ERRO: {mensagem_erro}")
        print("========================================") 

def cronometro():
    secunds_time = int(input("Digite quantos segundos precisa cronometrar: "))
    for time_secunds in range(0, secunds_time + 1):
        minutes = time_secunds // 60
        secunds = time_secunds % 60
        print(f"{minutes:02d}:{secunds:02d}")
        time.sleep(1)
    print("Concluido!")
    print("========================================")
    return
    
def conversor_temperatura():
    print("Conversões disponíveis:")
    print("========================================")
    print("1 - Celsius para Fahrenheit \n2 - Fahrenheit para Celsius \n3 - Celsius para Kelvin \n4 - Kelvin para Celsius \n5 - Fahrenheit para Kelvin")
    print("========================================")
    
    option = int(input("Digite o número da conversão desejada: "))
    
    if option == 1:
        celsius = float(input("Digite a temperatura em Celsius: "))
        fahrenheit = (celsius * 9/5) + 32
        print(f"{celsius:.2f}°C = {fahrenheit:.2f}°F")
    elif option == 2:
        fahrenheit = float(input("Digite a temperatura em Fahrenheit: "))
        celsius = (fahrenheit - 32) * 5/9
        print(f"{fahrenheit:.2f}°F = {celsius:.2f}°C")
    elif option == 3:
        celsius = float(input("Digite a temperatura em Celsius: "))
        kelvin = celsius + 273.15
        print(f"{celsius:.2f}°C = {kelvin:.2f}K")
    elif option == 4:
        kelvin = float(input("Digite a temperatura em Kelvin: "))
        celsius = kelvin - 273.15
        print(f"{kelvin:.2f}K = {celsius:.2f}°C")
    elif option == 5:
        fahrenheit = float(input("Digite a temperatura em Fahrenheit: "))
        kelvin = (fahrenheit - 32) * 5/9 + 273.15
        print(f"{fahrenheit:.2f}°F = {kelvin:.2f}K")
    else:
        print(f"🔴 ERRO: {DIGITE_UM_NUMERO_VALIDO_1_5}")
        
def sorteio_numeros():
    print("====== Sorteio de Números ======")
    minimun = int(input("Digite o valor mínimo do intervalo: "))
    maximum = int(input("Digite o valor máximo do intervalo: "))
    
    if maximum - minimun + 1 < 5:
        print("Intervalo muito pequeno! É necessário pelo menos 5 números diferentes.")
        return
    
    numbers_sorted = random.sample(range(minimun, maximum + 1), 5)
    
    print("Sorteando os números...\n")
    for numero in numbers_sorted:
        print(f"Número sorteado: {numero}")
        time.sleep(1)

    print("Fim do sorteio!")
    print("========================================")
    
    
def relogio_digital():
    print("====== Relógio Digital ======")
    for _ in range(10):
        current_time = datetime.now()
        time_format = current_time.strftime("%H:%M:%S")
        print(f"Hora atual: {time_format}")
        time.sleep(1)
    print("Fim")
    print("========================================")
    
def temporizador():
    secunds_time = int(input("Digite quantos segundos precisa cronometrar: "))
    for time_secunds in range(secunds_time, -1, -1):
        minutes = time_secunds // 60
        secunds = time_secunds % 60
        print(f"{minutes:02d}:{secunds:02d}")
        time.sleep(1)
    print("Tempo esgotado!")
    print("========================================")
    return

def operacoes_matematicas():
    while True:
        print("========================================")
        print("Calculadora: ")
        print("1 - Somar dois números \n2 - Subtrair dois números \n3 - Multiplicar dois números \n4 - Dividir dois números \n5 - Sair")
        print("========================================")

        option = int(input("Digite a opção: "))
        if option == 1:
            sum_numbers()
        elif option == 2:
            subtract_numbers()
        elif option == 3:
            multiply_numbers()
        elif option == 4:
            divide_numbers()
        elif option == 5:
            print("Saindo da calculadora!")
            print("========================================")
            break
        else:
            print(f"🔴 ERRO: {DIGITE_UM_NUMERO_VALIDO_1_5}")
    
# Funções Matemáticas
def sum_numbers():
    a = float(input("Digite o primeiro número: "))
    b = float(input("Digite o segundo número: "))
    print(f"Resultado da soma: {a + b}")

def subtract_numbers():
    a = float(input("Digite o primeiro número: "))
    b = float(input("Digite o segundo número: "))
    print(f"Resultado da subtração: {a - b}")

def multiply_numbers():
    a = float(input("Digite o primeiro número: "))
    b = float(input("Digite o segundo número: "))
    print(f"Resultado da multiplicação: {a * b}")

def divide_numbers():
    a = float(input("Digite o primeiro número: "))
    b = float(input("Digite o segundo número: "))
    if b == 0:
        print("Não é possível dividir por zero.")
    else:
        print(f"Resultado da divisão: {a / b}")