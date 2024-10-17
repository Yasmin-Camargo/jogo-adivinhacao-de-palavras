import { useEffect, useState } from 'react';
import { Box, Heading, Input, Button, Text, Stack, Card, CardBody, CardHeader } from '@chakra-ui/react';
import { useGame } from '../hooks/useGame';

export const GamePage = () => {
  const { word, setWord, message, startGame, checkWord } = useGame();
  const [description, setDescription] = useState(''); // Inicializa como ponto de interrogação

  const handleStartGame = async () => {
    const desc = await startGame();
    setDescription(desc);
  };

  return (
    <Box 
      textAlign="center"
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
    >
      <Heading as="h1" size="2xl" color="white" mb={8}>
        Jogo de Adivinhação de Palavras
      </Heading>
      
      <Box mt={8} maxW="lg" w="100%">
        <Card 
          align="center" 
          bg="whiteAlpha.800"  
          boxShadow="dark-lg" 
          borderRadius="lg" 
          transform="translateY(-5px)" 
          transition="transform 0.3s ease-in-out"
        >
          <CardHeader display="flex" flexDirection="column" alignItems="center">
            <Button 
              colorScheme="gray" 
              size="lg" 
              onClick={handleStartGame} 
              w="100%" 
              mb={4} 
            >
              Sortear nova palavra
            </Button>
            <Heading size="lg" color="black.700">
              {description}
            </Heading>
          </CardHeader>
          <CardBody>
            <span style={{ fontSize: '3rem' }}> ❓</span>
          </CardBody>
        </Card>
        
        <Stack spacing={4}>
          <Input
            placeholder="Digite sua tentativa"
            value={word}
            onChange={(e) => setWord(e.target.value)}
            mt={4}
            color="black"
            size='lg'
            bg={"white"} 
          />
        </Stack>
    
        <Button colorScheme="blue" size="md" onClick={() => checkWord(word)} mt={4}>
          Verificar
        </Button>
        
        <Text size='lg' color="white" mt={4}>
          {message}
        </Text>
      </Box>
    </Box>
  );
};
