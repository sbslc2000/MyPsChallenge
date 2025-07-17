#!/bin/bash

# 스크립트 사용법 안내 함수
usage() {
  echo "사용법: $0 <문제번호>"
  echo "예시: $0 1000"
  exit 1
}

# 1. 커맨드 라인 인수로 문제번호를 입력받는다.
if [ -z "$1" ]; then
  echo "오류: 문제번호를 입력해주세요."
  usage
fi

PROBLEM_NUMBER=$1
BASE_SRC_DIR="problems/boj/solved"
TARGET_BIN_BASE_DIR="bin/prob" # /bin 대신 프로젝트 상대 경로 bin 사용 권장

# 2. src/main/java/problems/boj/solved/no_{문제번호}_% 로 시작하는 디렉터리를 찾는다.
# find 명령어: -type d (디렉토리), -name "패턴" (이름 패턴), -print (결과 출력), -quit (첫 번째 결과만 찾고 종료)
PROBLEM_SRC_SUBDIR_PATTERN="no_${PROBLEM_NUMBER}_*"
# find는 여러 결과를 반환할 수 있으므로, 정확히 하나의 디렉토리를 가정하거나,
# 사용자가 선택하게 하거나, 첫 번째 것만 사용하는 등의 처리가 필요할 수 있습니다.
# 여기서는 첫 번째로 찾은 디렉토리를 사용합니다.
PROBLEM_SRC_DIR_CANDIDATE=$(find "$BASE_SRC_DIR" -type d -name "$PROBLEM_SRC_SUBDIR_PATTERN" -print -quit)

if [ -z "$PROBLEM_SRC_DIR_CANDIDATE" ]; then
  echo "오류: '${BASE_SRC_DIR}/${PROBLEM_SRC_SUBDIR_PATTERN}' 패턴에 해당하는 디렉터리를 찾을 수 없습니다."
  exit 1
fi

# 찾은 디렉토리가 여러 개일 경우, 첫 번째 것을 사용 (위의 find -quit 로 이미 처리됨)
# 만약 여러 개 중 선택하게 하려면 추가 로직 필요
echo "소스 디렉토리 확인: $PROBLEM_SRC_DIR_CANDIDATE"

MAIN_JAVA_FILE="${PROBLEM_SRC_DIR_CANDIDATE}/Main.java"

if [ ! -f "$MAIN_JAVA_FILE" ]; then
  echo "오류: '${MAIN_JAVA_FILE}' 파일을 찾을 수 없습니다."
  exit 1
fi

echo "컴파일 대상 파일: $MAIN_JAVA_FILE"

# 3. 그곳에는 Main.java가 있을 것이다. 이를 컴파일하여 class 파일을 /bin/prob/{문제번호} 에 저장한다.
# 패키지 구조를 유지하며 클래스 파일을 저장하기 위해 -d 옵션 사용
# 출력 디렉토리가 없다면 생성
TARGET_CLASS_OUTPUT_DIR="${TARGET_BIN_BASE_DIR}/${PROBLEM_NUMBER}"
mkdir -p "$TARGET_CLASS_OUTPUT_DIR" # -p 옵션: 필요시 상위 디렉토리까지 생성

echo "컴파일 중... 클래스 파일 저장 위치: ${TARGET_CLASS_OUTPUT_DIR}"

# javac 컴파일
# -sourcepath 옵션으로 소스 파일의 루트 경로를 지정하여 패키지 문제를 해결할 수 있습니다.
# 여기서는 Main.java 파일의 상위 디렉토리들 중 패키지 루트가 될 만한 곳을 지정합니다.
# 일반적으로 src/main/java 가 클래스패스 루트 또는 소스패스 루트가 됩니다.
# 컴파일 시 클래스패스에 프로젝트의 다른 의존성이 필요하다면 -cp 옵션 추가 필요.
# 여기서는 단일 파일 컴파일로 가정합니다.

# 패키지명을 추출해야 정확한 컴파일이 가능합니다. Main.java 파일 내의 package 선언을 읽어오거나,
# 또는 $PROBLEM_SRC_DIR_CANDIDATE 경로에서 $BASE_SRC_DIR 부분을 제외하여 얻을 수 있습니다.
# 예: problems/boj/solved/no_1000_astar/Main.java 라면
# 패키지는 problems.boj.solved.no_1000_astar 가 됩니다.

# javac 명령어는 보통 소스 파일의 루트 디렉토리에서 실행하거나, -sourcepath 를 정확히 지정해야 합니다.
# Main.java 파일이 있는 디렉토리로 이동해서 컴파일하거나,
# javac -d <출력디렉토리> <소스파일경로> 형태로 실행합니다.
# 패키지 구조를 올바르게 인식시키려면, javac가 소스 파일의 패키지를 알 수 있어야 합니다.

# 컴파일 시도 1: Main.java 파일이 있는 디렉토리를 기준으로 컴파일
# 이 방식은 Main.java 파일 내에 package 선언이 올바르게 되어 있고,
# 해당 패키지 구조가 src/main/java 아래에 정확히 일치할 때 잘 동작합니다.
# -d 옵션은 패키지 구조를 TARGET_CLASS_OUTPUT_DIR 아래에 그대로 생성합니다.
javac -encoding UTF-8 -d "$TARGET_CLASS_OUTPUT_DIR" "$MAIN_JAVA_FILE"

# 컴파일 성공 여부 확인
if [ $? -eq 0 ]; then
  echo "컴파일 성공!"
  echo "클래스 파일:"
  find "$TARGET_CLASS_OUTPUT_DIR" -name "*.class" -print
else
  echo "오류: 컴파일에 실패했습니다."
  # 이미 javac 에서 오류 메시지를 출력했을 것입니다.
  exit 1
fi

exit 0
