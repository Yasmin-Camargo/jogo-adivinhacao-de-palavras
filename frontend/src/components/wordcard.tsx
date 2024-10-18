import { WordData } from '../interface/WordData';

import {
  Card as ChakraCard,
  CardHeader,
  CardBody,
  CardFooter,
  Button,
  Heading,
  Text,
  ButtonGroup,
} from '@chakra-ui/react';

interface CardProps {
  wordData: WordData;
}

export const Card = ({ wordData }: CardProps) => {
  const capitalize = (text: string) => text.charAt(0).toUpperCase() + text.slice(1).toLowerCase();

  return (
    <ChakraCard bg="gray.50" borderWidth="1px" borderRadius="lg" boxShadow="xl">
      <CardHeader>
        <Heading size="md">{capitalize(wordData.word)}</Heading>
      </CardHeader>

      <CardBody>
        <Text>{capitalize(wordData.description)}</Text>
        <Text mt={2}>
          <b>Dica:</b> {wordData.synonymous.toLowerCase()}
        </Text>
        <Text mt={2} fontSize="sm" color="gray.500">
          Nível {wordData.level.toLowerCase()}
        </Text>
      </CardBody>

      <CardFooter>
        <ButtonGroup variant="outline" isAttached width="100%">
          <Button colorScheme="gray" size="md" width="50%">
            Editar
          </Button>
          <Button colorScheme="gray" size="md" width="50%">
            Excluir
          </Button>
        </ButtonGroup>
      </CardFooter>
      
    </ChakraCard>
  );
};
