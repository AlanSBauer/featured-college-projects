# Como executar o projeto (Windows + Git Bash)

## Por que `mvn` não funciona?

A mensagem `bash: mvn: command not found` significa que o **Apache Maven não está instalado** (ou não está no PATH).

Este projeto inclui o **Maven Wrapper** (`mvnw`). Você **não precisa** instalar o Maven manualmente — use `./mvnw` no Git Bash ou `mvnw.cmd` no CMD/PowerShell.

---

## Passo 1 — Usar um JDK (não só o JRE 8)

O erro `No compiler is provided` / `which: no javac` significa que o terminal está usando **Java 8 (JRE)** sem compilador.

### Opção A — JDK do IntelliJ (você já tem)

No seu PC o JDK está em:

`D:\IntelliJ IDEA 2026.1\jbr`

No **Git Bash**, na pasta do projeto:

```bash
source ./set-java.sh
```

Deve aparecer `openjdk version "25.x"` e `javac 25.x`.

### Opção B — Instalar JDK 17 separado

https://adoptium.net/temurin/releases/?version=17&os=windows&arch=x64&package=jdk

Depois:

```bash
export JAVA_HOME="/c/Program Files/Eclipse Adoptium/jdk-17.0.14.7-hotspot"
export PATH="$JAVA_HOME/bin:$PATH"
```

(Ajuste o caminho da pasta instalada.)

---

## Passo 2 — Compilar e rodar (Git Bash)

Na pasta do projeto:

```bash
cd "/d/Git e GitHub/arquitetura-software-quinta"

source ./set-java.sh
chmod +x mvnw
./mvnw clean compile
./mvnw exec:java
```

**Importante:** rode `source ./set-java.sh` em **cada** terminal novo antes do `./mvnw`.

Demo sem scraping (dados simulados):

```bash
./mvnw exec:java -Dexec.mainClass=MainDemo
```

---

## Passo 3 — Alternativa no CMD / PowerShell

```cmd
cd "d:\Git e GitHub\arquitetura-software-quinta"
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.14.7-hotspot
mvnw.cmd clean compile
mvnw.cmd exec:java
```

---

## Pré-requisito extra para o Main (crawler real)

O `Main` usa **curl** no PATH. No Git Bash geralmente já existe. Teste:

```bash
curl --version
```

---

## Resumo dos erros comuns

| Erro | Causa | Solução |
|------|--------|---------|
| `mvn: command not found` | Maven não instalado | Use `./mvnw` |
| `release version 17 not supported` | Java 8 no PATH | Instale JDK 17 e configure JAVA_HOME |
| `JAVA_HOME not found` (mvnw.cmd) | Variável não definida | `export JAVA_HOME=...` |
| Crawler sem preços | Sites bloqueiam CURL ou HTML dinâmico | Use `MainDemo` para apresentação |

---

## Rodar pela IDE (IntelliJ)

1. **File → Project Structure → Project SDK** → JDK 17.
2. Clique com o botão direito em `Main.java` → **Run**.
3. Ou use a aba Maven da IDE (ela traz Maven embutido).
