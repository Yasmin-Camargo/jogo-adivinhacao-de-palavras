// App.tsx
import { Box, Heading, Tabs, TabList, TabPanels, Tab, TabPanel } from '@chakra-ui/react';
import { GamePage } from './pages/game';
import { WordList } from './pages/wordList'; 

function App() {
  return (
    <Box maxW="1280px" mx="auto" p={8} textAlign="center">
      <Tabs isFitted variant="enclosed">
        <TabList mb="1em">
          <Tab color="white">Jogar</Tab>
          <Tab color="white">Palavras Cadastradas</Tab>
        </TabList>

        <TabPanels>
          <TabPanel>
            <GamePage />
          </TabPanel>
          <TabPanel>
            <WordList />
          </TabPanel>
        </TabPanels>
      </Tabs>
    </Box>
  );
}

export default App;
