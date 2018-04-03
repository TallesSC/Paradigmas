import Data.Char

-- Converte um caracter em um inteiro
encodeChar :: Char -> Int
encodeChar c = ord c - ord 'a'

-- Converte um inteiro em um caracter
decodeChar :: Int -> Char
decodeChar n = chr (ord 'a' + n)

-- Calcula percentagem: n/m*100
percent :: Int -> Int -> Float
percent n m = (fromIntegral n / fromIntegral m)*100

-- Rotacao de uma lista para esquerda em n posicoes
rotate :: Int -> [a] -> [a]
rotate n xs = drop n xs ++ take n xs

-- Tabela de frequencias das letras 'a'..'z' (lingua portuguesa)
-- https://pt.wikipedia.org/wiki/Frequ%C3%AAncia_de_letras
table :: [Float]
table = [14.6, 1.0, 3.9, 5.0, 12.6, 1.0, 1.3, 1.3, 6.2, 0.4, 0.1, 2.8, 4.7, 
         5.0, 10.7, 2.5, 1.2, 6.5, 7.8, 4.3, 4.6, 1.7, 0.1, 0.2, 0.1, 0.5]

-- Distancia entre 2 listas de frequencia
chi2 :: [Float] -> [Float] -> Float
chi2 os es = sum [((o-e)^2)/e | (o,e) <- zip os es]
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------

--1
--Usando encodeChar e decodeChar, defina uma função shiftChar :: Char -> Int -> Char. Essa função, ao receber um caracter de 'a' a 'z', aplicará um deslocamento de 'n' unidades sobre ele, produzindo outro caracter no intervalo ['a'..'z'].

shiftChar :: Char -> Int -> Char
shiftChar c n 
    | (encodeChar c > 25) || (encodeChar c < 0) = c
    | ((encodeChar c) + n) > 25 = decodeChar (((encodeChar c) + n) `mod` (encodeChar 'z' + 1))
    | n <= 25 = decodeChar (encodeChar (c) + n)

--2
--Usando shiftChar, defina uma função encodeStr :: String -> Int -> String que codifique uma string usando um dado deslocamento.

encodeStr :: String -> Int -> String
encodeStr string n = map (\x -> shiftChar x n) string

--3
--Defina uma função countValids :: String -> Int, que receba uma string e retorne a quantidade de seus caracteres contidos no intervalo ['a'..'z'].

countValidsAux :: Char -> Int
countValidsAux c = if elem c ['a'..'z'] then 1 else 0

countValids :: String -> Int
countValids "" = 0
countValids string = countValidsAux (head string) + countValids (tail string)

--4
--Defina uma função countChar :: Char -> String -> Int, que retorne a quantidade de um dado caracter em uma string. 

countCharAux :: Char -> Char -> Int
countCharAux c h = if c==h then 1 else 0

countChar :: Char -> String -> Int
countChar c "" = 0
countChar c string = (countCharAux c (head string)) + (countChar c (tail string))

--5
--Usando countValids, countChar e percent, defina uma função freqs :: String -> [Float] que retorne as frequências dos caracteres ['a'..'z'] numa dada string. Use list comprehension. A frequência de um caracter é dada pelo percentual deste caracter entre os caracteres válidos da string.

freqs :: String -> [Float]
freqs string = map (\x -> percent (countChar x string) (countValids string)) ['a'..'z']

--6
--Defina uma função positions :: Float -> [Float] -> [Int], que retorne uma lista de posições de um dado número em uma lista. Considere que as posições comecem em zero. Use a função zip como auxiliar no seu código.

aux list = zip [0..(length list)] list

positions :: Float -> [Float] -> [Int]
positions n list = fst(unzip (filter ((==n).snd) (aux list)))




