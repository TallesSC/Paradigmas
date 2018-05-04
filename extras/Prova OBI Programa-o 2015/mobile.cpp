#include <iostream>
#include <vector>
#include <fstream>
#include <stdlib.h>

// https://olimpiada.ic.unicamp.br/static/extras/obi2015/provas/ProvaOBI2015_f1pj.pdf
// PROVA OBI 2015 (Fase 1) - MÓBILE 

void printa_resultado(bool equilibrio){
  if (equilibrio) {
    std::cout << "S" << std::endl;
  }else{
    std::cout << "N" << std::endl;
  }
}

void verifica_equilibrio(int a, int b, int c, int d){
  bool equilibrio;

  if (a == b + c +d){
    equilibrio = true;
  }else{
    equilibrio = false;
  }

  if (b + c == d){
    equilibrio = true;
  }else{
    equilibrio = false;
  }

  if (b == c){
    equilibrio = true;
  }else{
    equilibrio = false;
  }

  printa_resultado(equilibrio);
}

int main(int argc, char const *argv[])
{
  int a = 1,b = 1,c = 1,d = 1;

  std::cout << "Digite o peso da bola A" << std::endl;
  std::cin >> a;
  std::cout << "Digite o peso da bola B" << std::endl;
  std::cin >> b;
  std::cout << "Digite o peso da bola C" << std::endl;
  std::cin >> c;
  std::cout << "Digite o peso da bola D" << std::endl;
  std::cin >> d;

  verifica_equilibrio(a,b,c,d);

  return 0;
}
