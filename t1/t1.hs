import Data.Char

--1. Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
isVowel :: Char -> Bool
isVowel c = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')

--2 
--Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
addComma :: [String] -> [String]
addComma string = map (\x -> x ++ ",") string

--3 
--Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML.

  -- Com função anônima
htmlListItems :: [String] -> [String]
htmlListItems string = map (\x -> "<LI>" ++ x ++ "</LI>") string
  -- Sem função anônima
aux :: String -> String
aux x = ("<LI>" ++ x ++ "</LI>")
htmlListItems2 :: [String] -> [String]
htmlListItems2 string = map aux string

--4 
--Defina uma função que receba uma string e produza outra retirando as vogais.

  -- Com função anônima
retiraVogais :: String -> String
retiraVogais string = filter (\x -> x/='a' && x/='e' && x/='i' && x/='o' && x/='u') string

  -- Sem função anônima
aux2 :: Char -> Bool
aux2 x = x/='a' && x/='e' && x/='i' && x/='o' && x/='u'
retiraVogais2 :: String -> String
retiraVogais2 string = filter aux2 string

--5
--Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços. 

  -- Com função anônima
codifica :: String -> String
codifica string = map (\x -> if x/= ' ' then '-' else x) string

  -- Sem função anônima
func :: Char -> Char
func c = if c/= ' ' then '-' else c
codifica2 :: String -> String
codifica2 string = map func string
  
--6
--Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome.
firstName :: String -> String
firstName string = takeWhile (/=' ') string

--7
--Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
isInt :: String -> Bool
isInt string = length (filter (\c -> c > '9' || c < '0') string) == 0

--8
--Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome.
lastName :: String -> String
lastName nome = reverse ( firstName (reverse nome))

--9
--Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas.
userName :: String -> String
userName nome = map (toLower) (take 1 nome ++ lastName nome)

--10
--Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
encodeChar :: Char -> Char
encodeChar c
  | (toLower c) == 'a' = '4'
  | (toLower c) == 'e' = '3'
  | (toLower c) == 'i' = '2'
  | (toLower c) == 'o' = '1'
  | (toLower c) == 'u' = '0'
  | otherwise = c

encodeName :: String -> String
encodeName nome = map encodeChar nome

--11
--Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00. 
betterEncodeChar :: Char -> String
betterEncodeChar c
  | (toLower c) == 'a' = "4"
  | (toLower c) == 'e' = "3"
  | (toLower c) == 'i' = "1"
  | (toLower c) == 'o' = "0"
  | (toLower c) == 'u' = "00"
  | otherwise = [c]
  
betterEncodeName :: String -> String
betterEncodeName string = concatMap (betterEncodeChar) string

--12
--Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres.
dezCaracteres :: [String] -> [String]
dezCaracteres lista = map (\s -> take 10 (s ++ "..........")) lista








