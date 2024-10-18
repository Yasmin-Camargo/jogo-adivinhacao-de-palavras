import { useState } from 'react';
import { Box, Heading, Input, Button, Text, Stack, Card, CardBody, CardHeader, Grid, Flex } from '@chakra-ui/react';
import { useGame } from '../hooks/useGame';

export const GamePage = () => {
  const { word, setWord, message, startGame, checkWord } = useGame();
  const [description, setDescription] = useState('');
  const [level, setLevel] = useState('');
  const [synonymous, setSynonymous] = useState('?');

  const handleStartGame = async () => {
    let body = await startGame();
    body = body.split(";");
    setDescription(body[0]);
    setLevel(body[1]);
    setSynonymous(body[2]);
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

      <Button 
        colorScheme="green" 
        size="md" 
        onClick={handleStartGame} 
        mb={4}
      >
        Sortear nova palavra
      </Button>

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
            <span style={{ fontSize: '3rem' }}> ❓</span>
            <Text>Dica: {synonymous}</Text>
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
    
      <Button colorScheme="blue" size="md" onClick={() => checkWord(word)} mt={4}>
        Verificar
      </Button>
      
      <Text size='lg' color="white" mt={4}>
        {message}
      </Text>
    </Box>
  );
};
