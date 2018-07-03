"""
https://github.com/AndreaInfUFSM/elc117-2018a/tree/master/praticas/oo/java2
Escreva um programa em Java que:

crie um array de 5 pontos e
calcule e mostre as distâncias de cada ponto em relação a todos os demais pontos.
Escreva outro programa em Java com a mesma funcionalidade do anterior, porém:

Keia o número de pontos da entrada padrão e
substitua o array pela classe ArrayList, que faz parte do framework Collections da linguagem Java
"""

import math

class Ponto:

    def __init__(self, X, Y):
        self.x = X
        self.y = Y

    def distance(self, ponto2):
        dist = math.hypot(ponto2.x - self.x, ponto2.y - self.y)
        print("Distância entre pontos (" + str(self.x) + "," + str(self.y) + ") e (" + str(ponto2.x) + "," + str(ponto2.y) + "): " + str(dist))


def main():
    list = []
    for i in range(0, 5):
        x = float(input("Digite o X do ponto"))
        y = float(input("Digite o Y do ponto"))
        p = Ponto(x,y)
        list.append(p)

    for p1,p2 in zip(list, list[1:]):
        for p2 in list:
            if p1 != p2:
                p1.distance(p2)


if __name__ == '__main__':
    main()
