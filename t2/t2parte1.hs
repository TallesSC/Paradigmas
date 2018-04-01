--1
--Usando recursão, escreva uma função geraTabela :: Int -> [(Int,Int)] que produza uma lista com n tuplas, cada tupla com números de n a 1 e seus respectivos quadrados. 

geraTabela :: Int -> [(Int,Int)]
geraTabela 1 = [(1, 1)]
geraTabela n = (n, n^2) : geraTabela (n-1)

--2
--Defina uma função recursiva que verifique se um dado caracter está contido numa string.

contido :: Char -> String -> Bool
contido c "" = False 
contido c string = 
    if (head string == c) then True 
    else contido c (tail string)
    
--3
--Defina uma função recursiva que receba uma lista de coordenadas de pontos 2D e desloque esses pontos em 2 unidades.

translate :: [(Float, Float)] -> [(Float, Float)]
translate [] = []
translate ponto = (fst (head ponto)+2, snd (head ponto)+2) : translate (tail ponto)

--4
--Defina uma função que receba um número n e retorne uma lista de n tuplas, cada tupla com números de 1 a n e seus respectivos quadrados.

geraQuadrado :: Int -> Int -> [(Int, Int)]
geraQuadrado n m = 
    if m > n then []
    else (m, m^2) : geraQuadrado n (m+1) 

geraTabela' :: Int -> [(Int, Int)]
geraTabela' n = geraQuadrado n 1








