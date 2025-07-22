def menu():
   print('Gerenciador de Tarefas iniciado!')
   while True:
      print('\nMenu de Opções: \n1 - Adicionar tarefa \n2 - Editar tarefa \n3 - Excluir tarefa \n4 - Concluir tarefa\n5 - Visualizar tarefas \n6 - Encerrar programa')
      try:
         opcao = int(input('\nDigite a opção: '))
      except ValueError:
         print('\nOpção inválida! Digite novamente.')
         continue

      if opcao == 1:
         add_tarefa()
      elif opcao == 2:
         edit_tarefa()
      elif opcao == 3:
         excluir_tarefa()
      elif opcao == 4:
         concluir_tarefa()
      elif opcao == 5:
         ver_tarefas()
      elif opcao == 6:
         print('Programa encerrado!')
         break
      else:
         print('\nOpção inválida! Digite novamente.')


def add_tarefa():
   global gerenciador_de_tarefas
   print('\nDigite a descrição da tarefa')
   print('Ou digite \"cancelar\" para cancelar')
   desc_tarefa = input('Descrição: ') 
   if desc_tarefa == 'cancelar' or desc_tarefa == 'Cancelar' or desc_tarefa == 'CANCELAR':
      return
   while True:
      try:
         relevancia = int(input('\nDigite a relevância da tarefa (1-5): '))
         if 1 <= relevancia <= 5:
            break
         else:
            print('\nVocê deve digitar um valor entre 1 e 5!')
      except ValueError:
         print('\nOpção inválida, você digitou um caractére! Digite novamente: ')
   while True:
      try:
         tempo_min = float(input('\nDigite o tempo estimado em minutos da tarefa: '))
         gerenciador_de_tarefas.append([desc_tarefa, relevancia, tempo_min])
         print('\nTarefa adicionada!')
         break
      except ValueError:
         print('\nOpção inválida, digite o número de minutos corretamente: ')


def edit_tarefa():
   global gerenciador_de_tarefas
   if not gerenciador_de_tarefas:
      print('\nVocê não tem nenhuma tarefa para editar!')
      return
   
   print('\nTarefas disponíveis para edição:')
   for idx in range(len(gerenciador_de_tarefas)):
      tarefa = gerenciador_de_tarefas[idx]
      print(f"{idx+1} - {tarefa[0]} (Relevância: {tarefa[1]}, Tempo: {tarefa[2]} min)")
   
   try:
      idx_editar = int(input('\nDigite o número da tarefa que deseja editar: ')) - 1
      if idx_editar < 0 or idx_editar >= len(gerenciador_de_tarefas):
         print('Índice inválido!')
         return
   except ValueError:
      print('Digite um número válido!')
      return
   
   tarefa = gerenciador_de_tarefas[idx_editar]
   while True:
      print('\n1 - Descrição\n2 - Relevância\n3 - Tempo')
      try:
         campo = int(input('Digite o campo que deseja editar (1-3): '))
         if campo < 1 or campo > 3:
            print('Opção inválida!')
      except ValueError:
         print('Digite um número válido!')
      
      if campo == 1:
         nova_desc = input('Digite a nova descrição: ')
         tarefa[0] = nova_desc
         break
      elif campo == 2:
         while True:
            try:
               nova_relevancia = int(input('Digite a nova relevância (1-5): '))
               if 1 <= nova_relevancia <= 5:
                  tarefa[1] = nova_relevancia
                  break
               else:
                  print('A relevância deve ser entre 1 e 5!')
            except ValueError:
               print('Digite um número válido!')
         break
      elif campo == 3:
         while True:
            try:
               novo_tempo = float(input('Digite o novo tempo estimado: '))
               tarefa[2] = novo_tempo
               break
            except ValueError:
               print('Digite um número válido!')
         break
      else:
         print('Tente novamente!')
      
   print('Tarefa editada com sucesso!')


def excluir_tarefa():
   global gerenciador_de_tarefas
   if not gerenciador_de_tarefas:
      print('\nVocê não tem nenhuma tarefa para excluir!')
      return
   
   print('\nTarefas disponíveis:')
   for idx in range(len(gerenciador_de_tarefas)):
      tarefa = gerenciador_de_tarefas[idx]
      print(f"{idx+1} - {tarefa[0]} (Relevância: {tarefa[1]}, Tempo: {tarefa[2]} min)")
   
   try:
      idx_excluir = int(input('\nDigite o número da tarefa que deseja excluir: ')) - 1
      confirmacao = input(f'Tem certeza que deseja excluir "{gerenciador_de_tarefas[idx_excluir][0]}"? (s/n): ')
      if confirmacao.lower() != 's':
         print('Exclusão cancelada.')
         return
      if idx_excluir < 0 or idx_excluir >= len(gerenciador_de_tarefas):
         print('Índice inválido!')
         return
   except ValueError:
      print('Digite um número válido!')
      return
   
   tarefa_removida = gerenciador_de_tarefas.pop(idx_excluir)
   print(f'Tarefa "{tarefa_removida[0]}" removida com sucesso!')



def concluir_tarefa():
   global gerenciador_de_tarefas, tarefas_finalizadas
   if not gerenciador_de_tarefas:
      print('\nVocê não tem nenhuma tarefa para concluir!')
      return
   
   print('\nTarefas pendentes:')
   for idx in range(len(gerenciador_de_tarefas)):
      tarefa = gerenciador_de_tarefas[idx]
      print(f"{idx+1} - {tarefa[0]} (Relevância: {tarefa[1]}, Tempo: {tarefa[2]} min)")
   
   try:
      idx_concluir = int(input('\nDigite o número da tarefa concluída: ')) - 1
      if idx_concluir < 0 or idx_concluir >= len(gerenciador_de_tarefas):
         print('Índice inválido!')
         return
   except ValueError:
      print('Digite um número válido!')
      return
   
   tarefa_concluida = gerenciador_de_tarefas.pop(idx_concluir)
   tarefas_finalizadas.append(tarefa_concluida)
   print(f'Tarefa "{tarefa_concluida[0]}" marcada como concluída!')

def ver_tarefas():
   print('\n1 - Tarefas Pendentes\n2 - Tarefas Concluídas')
   try:
      escolha = int(input('Digite (1/2): '))
   except ValueError:
      print('Opção inválida!')
      return
   
   if escolha == 1:
      tarefas_pendentes()
   elif escolha == 2:
      tarefas_concluidas()
   else:
      print('Opção inválida!')



def tarefas_pendentes():
   global gerenciador_de_tarefas
   if not gerenciador_de_tarefas:
      print('\nNenhuma tarefa pendente!')
   else:
      print('\nTarefas PENDENTES:')
      for i in range(len(gerenciador_de_tarefas)):
         tarefa = gerenciador_de_tarefas[i]
         print(f"{i+1} - {tarefa[0]} (Relevância: {tarefa[1]}, Tempo: {tarefa[2]} min)")



def tarefas_concluidas():
   global tarefas_finalizadas
   if not tarefas_finalizadas:
      print('\nNenhuma tarefa concluída!')
   else:
      print('\nTarefas CONCLUÍDAS:')
      for i in range(len(tarefas_finalizadas)):
         tarefa = tarefas_finalizadas[i]
         print(f"{i+1} - {tarefa[0]} (Relevância: {tarefa[1]}, Tempo: {tarefa[2]} min)")

#------------------------------------------------------------------------

gerenciador_de_tarefas = []
tarefas_finalizadas = []

menu()
