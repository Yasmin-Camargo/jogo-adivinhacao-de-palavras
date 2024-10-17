import { Box, Button, useDisclosure } from '@chakra-ui/react';
import { useWordData } from '../hooks/useWordData';
import { Card } from '../components/wordcard';
import WordModal from '../components/wordform'; 
import { WordData } from '../interface/WordData';

export const WordList = () => {
  const { data } = useWordData();
  const { isOpen, onOpen, onClose } = useDisclosure(); 

  const handleSave = (newWordData: WordData) => {
    console.log('Nova palavra adicionada:', newWordData);
    // lógica para salvar a nova palavra 
  };

  return (
    <Box maxW="1280px" mx="auto" p={8} textAlign="center">
      <Button colorScheme="green" size="md" onClick={onOpen}>
        +
      </Button>
      <WordModal isOpen={isOpen} onClose={onClose} onSave={handleSave} />

      <Box
        display="grid"
        gridTemplateColumns="repeat(auto-fill, minmax(250px, 1fr))"
        gap={4}
        mt={8}
      >
        {data?.map((wordData, index) => (
          <Card key={wordData.id || index} wordData={wordData} />
        ))}
      </Box>
    </Box>
  );
};
