import { Box, Button, useDisclosure } from '@chakra-ui/react';
import { useWordData } from '../hooks/useWordData';
import { Card } from '../components/wordcard';
import WordForm from '../components/wordform';
import { WordData } from '../interface/WordData';
import { saveWord } from '../hooks/useWordForm'; 

export const WordList = () => {
  const { data, refetch } = useWordData();
  const { isOpen, onOpen, onClose } = useDisclosure();

  const handleSave = async (newWordData: WordData) => { 
    try {
      await saveWord(newWordData);
      refetch(); 
    } catch (error) {
      console.error('Erro ao adicionar palavra:', error);
    }
  };

  return (
    <Box maxW="1280px" mx="auto" p={8} textAlign="center">
      <Button colorScheme="green" size="md" onClick={onOpen}>
        +
      </Button>
      <WordForm isOpen={isOpen} onClose={onClose} onSave={handleSave} />
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
