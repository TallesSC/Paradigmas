#include <iostream>
#include <vector>
#include <fstream>
#include <stdlib.h>

// https://olimpiada.ic.unicamp.br/static/extras/obi2015/provas/ProvaOBI2015_f1pj.pdf
// PROVA OBI 2015 (Fase 1) - PREMIO DO MILHÃO 

int main(int argc, char const *argv[])
{
  int k=0,n, aux, soma = 0;
  std::vector<int> acessos;

  std::cin >> n;
  for (int i = 0; i < n; i++){
    std::cin >> aux;
    soma += aux;
    if (soma >= 1000000){
      k = i;
    }
    acessos.push_back(aux);
  }
  std::cout << k << std::endl;


  return 0;
}
