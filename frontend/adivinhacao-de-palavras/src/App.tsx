import { Box, Heading, Button } from '@chakra-ui/react';
import { useWordData } from './hooks/useWordData';
import { Card } from './components/card/wordcard';

function App() {
  const { data } = useWordData();

  return (
    <Box maxW="1280px" mx="auto" p={8} textAlign="center">
      <Heading as="h1" size="2xl" color="white" mb={4}>
        Palavras Cadastradas
      </Heading>
      
      <Box textAlign="right" mb={4}>
        <Button colorScheme="green" size="md" my={1}>
          +
        </Button>
      </Box>
      
      <Box
        display="grid"
        gridTemplateColumns="repeat(auto-fill, minmax(250px, 1fr))"
        gap={4}
        mt={8}
      >
        {data?.map(wordData => (
          <Card key={wordData.id} wordData={wordData} />
        ))}
      </Box>
    </Box>
  );
}

export default App;
