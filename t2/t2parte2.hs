--1
--Escreva uma função recursiva isBin :: String -> Bool para verificar se uma dada String representa um número binário, ou seja, contém apenas caracteres '0' ou '1'. As únicas funções pré-definidas autorizadas aqui são head e tail. 

verifica :: String -> Bool
verifica "" = True
verifica num = if (head num) == '0' || (head num) == '1' then verifica (tail num) else False

isBin :: String -> Bool
isBin num = if num /= "" && verifica num then True else False

--2
--Reescreva a função acima de forma não-recursiva. Dê outro nome para ela, por exemplo isBin'. Aqui você pode usar quaisquer funções auxiliares pré-definidas em Haskell.

isBin' :: String -> Bool
isBin' num = if length num == 0 then False else all (\x -> x=='0' || x=='1') num

--3
--Encontra-se abaixo a definição parcial da função bin2dec :: [Int] -> Int, que converte uma lista de 0's e 1's (representando um número binário), em seu equivalente em decimal.

bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)

--Implemente a função auxBin2Dec de forma recursiva, para que bin2dec funcione corretamente.

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] exp = 0
auxBin2Dec bit exp = (head bit)*(2^exp) + auxBin2Dec (tail bit) (exp-1)

--4
--Reescreva a função do exercício anterior de forma não-recursiva, usando funções pré-definidas em Haskell. Dê outro nome para a função (por exemplo, bin2dec').

--bin2dec' :: [Int] -> Int

--5
--Crie uma função recursiva dec2bin :: Int -> [Int] que receba um número inteiro positivo e retorne sua representação em binário, sob forma de uma lista de 0's e 1's. As funções auxiliares autorizadas aqui são mod, div e reverse.

dec2bin' :: Int -> [Int]
dec2bin' 0 = [0]
dec2bin' 1 = [1]
dec2bin' num = num `mod` 2 : dec2bin' (num `div` 2)

dec2bin :: Int -> [Int]
dec2bin num = reverse (dec2bin' num)

--6
--Implemente uma dessas funções: isHex :: String -> Bool ou hex2dec :: String -> Int ou dec2hex :: Int -> String, que são semelhantes às dos exercícios anteriores, porém com números hexadecimais no lugar de números binários. Aqui está tudo liberado: você pode escolher qual das funções irá implementar, sem restrições sobre como deve fazer isso.

isHexAux :: Char -> Bool 
isHexAux c
    | c >= '0' && c <= '9' = True
    | (toLower c) >= 'a' && (toLower c) <= 'f' = True
    | otherwise = False

isHex :: String -> Bool
isHex num = if length num == 0 then False else all (isHexAux) num







