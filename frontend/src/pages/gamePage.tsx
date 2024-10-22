import { useState } from 'react';
import { Box, Heading, Input, Button, Text, Stack, Card, CardBody, CardHeader, Flex, HStack } from '@chakra-ui/react';
import { ViewIcon } from '@chakra-ui/icons';
import { startGame, useCheckWord } from '../hooks/useGame';

export const GamePage = () => {
  const [word, setWord] = useState('');
  const [description, setDescription] = useState('');
  const [synonymous, setSynonymous] = useState('?');
  const [showHint, setShowHint] = useState(false);
  const [showDescription, setShowDescription] = useState(false);
  const [isGameStarted, setIsGameStarted] = useState(false); 

  const { message, setMessage, finish, setFinish, checkWord } = useCheckWord();

  const handleStartGame = () => {
    startGame()
      .then(body => {
        setDescription(body.description);
        setSynonymous(body.synonymous);
        setMessage('');
        setFinish(false);
        setShowHint(false);
        setShowDescription(true);
        setIsGameStarted(true);
      })
      .catch(error => {
        console.error('Erro ao iniciar o jogo:', error);
      });
  };

  const handleCheckWord = async () => {
    await checkWord(word);  
    if (finish) {
      setIsGameStarted(false); 
    }
    setWord(''); 
  };

  const handleShowHint = async () => {
    setShowHint(true);
    await checkWord('showsynonymous');
  };

  return (
    <Box maxW="1280px" mx="auto" p={8} textAlign="center">
      <Heading 
        as="h1" 
        size="2xl" 
        color="white" 
        mb={8}
        textShadow="2px 2px 4px rgba(0, 0, 0, 0.5)"
      >
        Jogo de Adivinhação de Palavras
      </Heading>

      <Flex justify="center">
        <Card 
          align="center" 
          bg="whiteAlpha.800"  
          boxShadow="dark-lg" 
          borderRadius="lg" 
          transform="translateY(-5px)" 
          transition="transform 0.3s ease-in-out"
          maxW="500px" 
          width="100%" 
        >
          <CardHeader display="flex" flexDirection="column" alignItems="center">
            <Heading size="lg" color="black.700">
              {description}
            </Heading>
          </CardHeader>
          <CardBody>
            <span style={{ fontSize: '3rem' }}>❓</span>

            {showDescription ? (
              <HStack spacing={2}>
                <Text fontWeight="bold">Dica:</Text>
                {!showHint ? (
                  <ViewIcon w={8} h={8} cursor="pointer" onClick={handleShowHint} color="gray" />
                ) : (
                  <Text>{synonymous}</Text>
                )}
              </HStack>
            ) : (
              <Box>
                <Text as='b'>
                  Você tem 3 tentativas para adivinhar a palavra.
                </Text>
                <br />
                <Text as='kbd'>
                  Lembre-se: usar uma dica contará como uma tentativa.
                </Text>
              </Box>
            )}
            
          </CardBody>
        </Card>
      </Flex>

      <Stack spacing={4} maxW="500px" mx="auto" mt={4}>
        <Input
          placeholder="Digite sua tentativa"
          value={word}
          onChange={(e) => setWord(e.target.value)}
          color="black"
          size='lg'
          bg={"white"} 
        />
      </Stack>
    
      <Button 
        colorScheme={isGameStarted ? "blue" : "green"} 
        size="md" 
        onClick={isGameStarted ? handleCheckWord : handleStartGame} 
        mt={4}
      >
        {isGameStarted ? "Verificar" : "Sortear nova palavra"} 
      </Button>
      
      <Text size='lg' color="white" mt={4}>
        {message}
      </Text>
    </Box>
  );
};
