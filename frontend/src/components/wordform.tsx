// WordModal.js
import React from 'react';
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  FormControl,
  FormLabel,
  Input,
  Select,
  Button,
} from '@chakra-ui/react';

const WordModal = ({ isOpen, onClose, onSave }) => {
  const initialRef = React.useRef(null);

  const [word, setWord] = React.useState('');
  const [description, setDescription] = React.useState('');
  const [hint, setHint] = React.useState('');
  const [level, setLevel] = React.useState('');

  const handleSave = () => {
    const newWordData = {
      word,
      description,
      hint,
      level,
    };

    onSave(newWordData); // Chama a função de salvar recebida como prop
    onClose(); // Fecha o modal
    // Reseta os campos após salvar
    setWord('');
    setDescription('');
    setHint('');
    setLevel('');
  };

  return (
    <Modal initialFocusRef={initialRef} isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Adicionar nova palavra</ModalHeader>
        <ModalCloseButton />
        <ModalBody pb={6}>
          <FormControl isRequired>
            <FormLabel>Palavra</FormLabel>
            <Input
              ref={initialRef}
              value={word}
              onChange={(e) => setWord(e.target.value)}
              placeholder='Palavra para ser adivinhada'
            />
          </FormControl>

          <FormControl isRequired mt={4}>
            <FormLabel>Descrição</FormLabel>
            <Input
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder='Descrição da palavra'
            />
          </FormControl>

          <FormControl isRequired mt={4}>
            <FormLabel>Dica</FormLabel>
            <Input
              value={hint}
              onChange={(e) => setHint(e.target.value)}
              placeholder='Dica (uma palavra)'
            />
          </FormControl>

          <FormControl isRequired mt={4}>
            <FormLabel>Leve</FormLabel>
            <Select
              value={level}
              onChange={(e) => setLevel(e.target.value)}
              placeholder='Selecione o nível'
            >
              <option value="1">1</option>
              <option value="2">2</option>
            </Select>
          </FormControl>
        </ModalBody>

        <ModalFooter>
          <Button colorScheme="blue" mr={3} onClick={handleSave}>
            Salvar
          </Button>
          <Button onClick={onClose}>Cancelar</Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};

export default WordModal;
