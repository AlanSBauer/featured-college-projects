#!/bin/sh
# JDK usado pelo IntelliJ (ajuste se mudar a versao)
if [ -d "/c/Users/Alan Bauer/.jdks/ms-25.0.3" ]; then
  export JAVA_HOME="/c/Users/Alan Bauer/.jdks/ms-25.0.3"
elif [ -d "/d/IntelliJ IDEA 2026.1/jbr" ]; then
  export JAVA_HOME="/d/IntelliJ IDEA 2026.1/jbr"
else
  echo "JDK nao encontrado. Instale JDK 17+ ou configure JAVA_HOME manualmente."
  return 1 2>/dev/null || exit 1
fi

export PATH="$JAVA_HOME/bin:$PATH"

echo "JAVA_HOME=$JAVA_HOME"
java -version
javac -version
