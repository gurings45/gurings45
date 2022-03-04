#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mem.h>

#define MAX_SIZE 100
#define SUBJECT 3

struct student_info{
    char name[10];
    int id;
    int gender;
    int c;
    int java;
    int kotlin;
    int total;
    double avr;
    char grade;
    int rank;
};

//구조체 타입 재정의
typedef struct student_info STUDENT;


void input_score(STUDENT *student);

void score_print(STUDENT *student, int count);

char calculate_grade(double avr);

void sort_student(STUDENT *student);

void insert_student(STUDENT *student);

int total_count = 0;

int main()
{
    int menu = 0;
    int flag = 0;

    printf("등록할 전체 인원수 입력 : ");
    scanf("%d",&total_count);

    //성적입력 데이타
    STUDENT student[MAX_SIZE];

    while(!flag){
        printf("1.성적입력(자동) \t");
        printf("2.성적출력       \t");
        printf("3.성적검색       \n");
        printf("4.나가기         \t");
        printf("5.화면초기화     \t");
        printf("6.성적삭제       \n");
        printf("7.성적수정       \t");
        printf("8.성적정렬(오름) \t");
        printf("9.성적삽입(수동) \n");
        printf("메뉴선택 : ");
        scanf("%d",&menu);

        switch(menu){
            case 1:
                input_score(student);
                printf("성적 입력 완료했습니다.");
                getchar();
                getchar();
                system( "cls" );
                break;
            case 2:
                score_print(student, total_count);
                getchar();
                getchar();
                system( "cls" );
                break;
            case 3:
                search_score(student);
                getchar();
                getchar();
                system( "cls" );
                break;
            case 4: flag =1;  printf("성적종료 선택함. \n"); break;
            case 5: system( "cls" ); break;
            case 6:
                remove_student(student);
                getchar();
                getchar();
                system( "cls" );
                break;
           case 7:
                update_student(student);
                getchar();
                getchar();
                system( "cls" );
                break;
           case 8:
                sort_student(student);
                getchar();
                getchar();
                system( "cls" );
                break;
           case 9:
                insert_student(student);
                getchar();
                getchar();
                system( "cls" );
                break;
            default: printf("정신차려 \n");
        }
    }//end of while

    printf("성적프로그램 종료 되었음. ");
    return 0;
}

void input_score(STUDENT *student){
    int i=0;
    for(i=0 ;i<total_count ;i++){
        student[i].name[0] = rand()%26 +'A';
        student[i].name[1] = rand()%26 +'A';
        student[i].name[2] = rand()%26 +'A';
        student[i].name[3] = 0;

        student[i].id = rand()%(9999-1000+1) + 1000;
        student[i].gender = rand()%2;
        student[i].c = rand()%101;
        student[i].java = rand()%101;
        student[i].kotlin = rand()%101;

        student[i].total =  student[i].c + student[i].java + student[i].kotlin;
        student[i].avr = student[i].total / (double)SUBJECT;

        student[i].grade = calculate_grade(student[i].avr);
    }
    return;
}

void score_print(STUDENT *student, int count){
    int i = 0;

    for(i=0 ;i<count ;i++){
        printf("%3d %5s %6d %3s %3d %3d %3d %5d %6.2lf %3c \n",
               i+1,student[i].name, student[i].id, (student[i].gender==1)?"남":"여",
               student[i].c, student[i].java, student[i].kotlin, student[i].total,
               student[i].avr, student[i].grade );
    }

    return;
}

char calculate_grade(double avr){
    char grade = 0;

    if(avr >= 90.0){
        grade = 'A';
    }else if(avr >= 80.0){
        grade = 'B';
    }else if(avr >= 70.0){
        grade = 'C';
    }else if(avr >= 60.0){
        grade = 'D';
    }else{
        grade = 'F';
    }
    return grade;
}

void search_score(STUDENT *student){
    int find_flag = 0;
    char name[10]={0,};
    int i=0;

    printf("검색할 학생 이름(대소문자 구분 없음) : ");
    scanf("%s",&name[0]);

    for(i=0 ;i<total_count ;i++ ){
        //문자열 같으면 0 리턴 대소문자 구별하지 않음.
        if(!strnicmp(&student[i].name[0],&name[0],3)){
            score_print(&student[i], 1);
            find_flag = 1;
        }
    }

    //찾는 학생 이름이 없을 경우 find_flag : 0
    if(!find_flag){
        printf("해당되는 이름이 없습니다. \n");
    }

    return;
}

void remove_student(STUDENT *student){
    int find_flag=0;
    char name[10]={0,};
    int i=0;

    printf("삭제할 학생 이름 입력(대소문자 구분없음) : ");
    scanf("%s",&name[0]);

    for(i=0 ;i<total_count ;i++){
        if(!strnicmp(student[i].name,name,3)){
            strcpy(student[i].name,"");
            student[i].id=0;
            student[i].gender=0;
            student[i].c=0;
            student[i].java=0;
            student[i].kotlin=0;
            student[i].total=0;
            student[i].avr=0.0;
            student[i].rank=0;

            find_flag=1;
        }
    }

    if(!find_flag){
        printf("삭제 해야할 해당되는 이름이 없습니다. \n");
    }else{
        printf("삭제완료 했습니다. \n");
    }

    return;


}

void update_student(STUDENT *student){

    int find_flag=0;
    char name[10]={0,};
    int i=0;

    printf("수정할 학생 이름 입력(대소문자 구분없음) : ");
    scanf("%s",&name[0]);

    for(i=0 ;i<total_count ;i++){
        if(!strnicmp(student[i].name,name,3)){
            printf("%s c점수(%d) 수정점수 : ",student[i].name, student[i].c);
            scanf("%d",&student[i].c);
            printf("%s java점수(%d) 수정점수 : ",student[i].name, student[i].java);
            scanf("%d",&student[i].java);
            printf("%s kotlin점수(%d) 수정점수 : ",student[i].name, student[i].kotlin);
            scanf("%d",&student[i].kotlin);
            student[i].total = student[i].c + student[i].java + student[i].kotlin;
            student[i].avr = student[i].total / (double)SUBJECT;
            student[i].grade= calculate_grade(student[i].avr);

            find_flag=1;

        }
    }

    if(!find_flag){
        printf("수정할 해당되는 이름이 없습니다. \n");
    }else{
        printf("수정완료 했습니다. \n");
    }

    return;
}

void sort_student(STUDENT *student){
    int i=0, j=0, sort=0;

    STUDENT buffer_student;

    STUDENT copy_student[total_count];
    for(i=0 ;i<total_count ;i++ ){
      memcpy(&copy_student[i], &student[i], sizeof(student[i]));
    }

    printf("오름차순(0), 내림차순(1) : ");
    scanf("%d",&sort);

    //오름차순
    if(sort == 0){
         for(i=0; i<total_count-1; i++){
           for(j=i+1 ;j<total_count ;j++ ){
                if(copy_student[i].total >  copy_student[j].total){
                    memcpy(&buffer_student, &copy_student[i], sizeof(buffer_student));
                    memcpy(&copy_student[i], &copy_student[j], sizeof(buffer_student));
                    memcpy(&copy_student[j], &buffer_student, sizeof(buffer_student));
                }
           }
         }//end of for
    }else{
    //내림차순
        for(i=0; i<total_count-1; i++){
           for(j=i+1 ;j<total_count ;j++ ){
                if(copy_student[i].total <  copy_student[j].total){
                    memcpy(&buffer_student, &copy_student[i], sizeof(buffer_student));
                    memcpy(&copy_student[i], &copy_student[j], sizeof(buffer_student));
                    memcpy(&copy_student[j], &buffer_student, sizeof(buffer_student));
                }
           }
         }
    }

    score_print(copy_student,total_count);

    return;

}

void insert_student(STUDENT *student){

    if(total_count +1 >= MAX_SIZE){
        printf("사이즈 추가로 추가할수 없습니다.! \n");
        return;
    }
    total_count++;

    printf("삽입할 학생 이름(3자 (A~Z) : ");
    scanf("%s",&student[total_count-1].name[0]);
    printf("삽입할 학생 번호(4자 (1000~9999) : ");
    scanf("%d",&student[total_count-1].id);
    printf("삽입할 학생 성별(여:0, 남:1) : ");
    scanf("%d",&student[total_count-1].gender);
    printf("삽입할 학생 C 점수(0~100) : ");
    scanf("%d",&student[total_count-1].c);
    printf("삽입할 학생 java 점수(0~100) : ");
    scanf("%d",&student[total_count-1].java);
    printf("삽입할 학생 kotlin 점수(0~100) : ");
    scanf("%d",&student[total_count-1].kotlin);

    student[total_count-1].total= student[total_count-1].c + student[total_count-1].java + student[total_count-1].kotlin;
    student[total_count-1].avr= student[total_count-1].total / (double)SUBJECT;
    student[total_count-1].grade= calculate_grade(student[total_count-1].avr);

    printf("%s 학생 점수가 삽입이 완료 되었습니다. \n",student[total_count-1].name);

    return;
}
