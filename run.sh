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
COMPILED_CLASSES_BASE_DIR="bin/prob"
PROBLEM_CLASSES_DIR="${COMPILED_CLASSES_BASE_DIR}/${PROBLEM_NUMBER}"

if [ ! -d "$PROBLEM_CLASSES_DIR" ]; then
  echo "오류: 컴파일된 클래스 디렉토리 '${PROBLEM_CLASSES_DIR}'를 찾을 수 없습니다."
  echo "'compile_boj.sh ${PROBLEM_NUMBER}'를 먼저 실행했는지 확인해주세요."
  exit 1
fi

MAIN_CLASS_FILE_PATH=$(find "$PROBLEM_CLASSES_DIR" -name "Main.class" -print -quit)

if [ -z "$MAIN_CLASS_FILE_PATH" ]; then
  echo "오류: '${PROBLEM_CLASSES_DIR}' 디렉토리 내에서 Main.class 파일을 찾을 수 없습니다."
  exit 1
fi

RELATIVE_CLASS_PATH=${MAIN_CLASS_FILE_PATH#"$PROBLEM_CLASSES_DIR/"}
FQCN_TEMP=${RELATIVE_CLASS_PATH%".class"}
FULLY_QUALIFIED_MAIN_CLASS_NAME=${FQCN_TEMP//\//.}

if [ -z "$FULLY_QUALIFIED_MAIN_CLASS_NAME" ]; then
  echo "오류: 메인 클래스의 전체 이름(FQCN)을 결정할 수 없습니다."
  exit 1
fi

CLASS_PATH_ROOT="$PROBLEM_CLASSES_DIR"

echo "======================================================"
echo "Java 프로그램 반복 실행기 (문제 번호: ${PROBLEM_NUMBER})"
echo "실행 대상 클래스: $FULLY_QUALIFIED_MAIN_CLASS_NAME"
echo "클래스패스: $CLASS_PATH_ROOT"
echo "종료하려면 Ctrl+C를 누르세요."
echo "======================================================"
echo ""

# 입력과 출력을 구분하기 위한 색상 코드 (선택 사항)
# tput을 사용하면 터미널 호환성이 더 좋지만, 간단히 ANSI 이스케이프 코드를 사용합니다.
COLOR_RESET='\033[0m'
COLOR_INPUT_PROMPT='\033[1;36m' # 밝은 청록색 (굵게)
COLOR_OUTPUT_HEADER='\033[1;32m' # 밝은 녹색 (굵게)
COLOR_ERROR='\033[1;31m' # 밝은 빨간색 (굵게)

# 4. 무한루프로 실행
while true; do
  # 5. 사용자 입력 프롬프트 및 입력 받기
  # -e 옵션은 백슬래시 이스케이프 시퀀스(색상 코드)를 해석하도록 합니다.
  # -p 옵션은 프롬프트 문자열을 지정합니다.
  echo -e "${COLOR_INPUT_PROMPT}입력 (여러 줄 입력 시 EOF로 종료, 한 줄 입력은 Enter):${COLOR_RESET}"

  # 여러 줄 입력을 받기 위한 방법 (Ctrl+D 또는 지정된 구분자로 종료)
  # 여기서는 간단히 read -r 로 한 줄씩 받거나,
  # 복잡한 입력은 사용자가 직접 EOF 등을 사용하여 입력하도록 안내합니다.
  # 대부분의 BOJ 문제는 한 번에 모든 입력을 처리하므로, 여기서는 여러 줄 입력을 위한 준비를 합니다.
  # 사용자가 입력을 마치면 빈 줄에서 Enter를 치거나, 특정 종료 문자열을 입력하도록 유도할 수 있습니다.
  # 여기서는 read -p 로 한 줄씩 입력받고, 사용자가 여러 줄을 입력하고 싶으면
  # 터미널에서 직접 개행하며 입력 후 마지막에 Enter를 치는 방식을 가정합니다.
  # 또는, here document를 활용하여 입력을 미리 준비하도록 안내할 수도 있습니다.

  # 한 줄 입력 방식:
  # read -r -p "입력: " user_input

  # 여러 줄 입력을 받기 (사용자가 EOF를 입력할 때까지)
  # IFS= backups the IFS variable, and then we clear it to prevent
  # leading/trailing whitespace stripping by `read` by default.
  # -d '' tells read to continue until a NUL byte, effectively reading everything.
  # We use a timeout of 0.1s to make it non-blocking if there's no immediate heredoc.
  # This is a bit more complex; for typical BOJ, often one line or a few known lines.

  # 가장 간단한 여러 줄 입력 방식은 사용자가 직접 여러 줄을 입력하고,
  # 스크립트가 이를 프로그램에 전달하는 것입니다.
  # 여기서는 read -a 로 배열로 받아서 합치는 방식을 사용해보겠습니다. (여러 줄 입력 처리가 필요하다면)
  # 다만 BOJ는 보통 한 번에 전체 입력을 받으므로, 파이프를 사용하는 것이 더 적합합니다.

  # 여기서는 사용자가 입력을 터미널에 직접 입력하고 (개행 포함 가능),
  # 그 입력을 Java 프로그램의 표준 입력으로 전달하는 방식을 사용합니다.
  # 사용자가 입력 완료 후 Enter를 치면, 그동안 입력된 모든 내용이 전달됩니다.
  # read 명령어는 기본적으로 한 줄만 읽으므로, 여러 줄 입력은 사용자가
  # 터미널에서 라인 연속 문자(\)를 사용하거나, 프로그램이 내부적으로 여러 번 읽도록 짜야 합니다.
  # 여기서는 `cat`을 이용하여 사용자가 Ctrl+D를 누를 때까지 입력을 받도록 합니다.
  
  echo "(입력 후 Ctrl+D를 눌러 입력을 종료하세요)"
  USER_INPUT=$(cat) # 사용자가 Ctrl+D를 누를 때까지의 모든 입력을 받음

  if [ -z "$USER_INPUT" ] && [ $? -ne 0 ]; then
    # Ctrl+D 만 눌러서 EOF가 발생했지만, 아무 입력도 없었을 경우 (또는 오류)
    # 사용자가 Ctrl+C를 눌렀을 수도 있으므로, 루프를 중단할 수 있도록 처리
    echo -e "\n${COLOR_ERROR}입력이 없거나 오류 발생. 종료합니다.${COLOR_RESET}"
    break
  fi
  
  echo -e "\n${COLOR_OUTPUT_HEADER}--- 프로그램 출력 ---${COLOR_RESET}"
  
  # 5. Java 실행 및 입력 전달, 출력 표시
  # 파이프(|)를 사용하여 사용자 입력을 Java 프로그램의 표준 입력으로 전달
  # 또는 here string (<<<) 사용: java -cp "$CLASS_PATH_ROOT" "$FULLY_QUALIFIED_MAIN_CLASS_NAME" <<< "$USER_INPUT"
  # here string은 변수 내 개행을 잘 처리합니다.
  echo "$USER_INPUT" | java -cp "$CLASS_PATH_ROOT" "$FULLY_QUALIFIED_MAIN_CLASS_NAME"
  
  # Java 프로그램 실행 후 종료 코드 확인 (선택 사항)
  # exit_status=$?
  # if [ $exit_status -ne 0 ]; then
  #   echo -e "${COLOR_ERROR}--- 프로그램 비정상 종료 (종료 코드: $exit_status) ---${COLOR_RESET}"
  # fi

  echo -e "${COLOR_OUTPUT_HEADER}--- 출력 종료 ---${COLOR_RESET}"
  echo "" # 다음 입력을 위해 한 줄 띄우기
done

echo -e "\n${COLOR_INPUT_PROMPT}스크립트를 종료합니다.${COLOR_RESET}"
exit 0
