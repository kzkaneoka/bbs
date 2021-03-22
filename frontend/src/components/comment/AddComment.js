import React, { Fragment, useState } from 'react';

import {
  Button,
  FormControl,
  Textarea,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  HStack,
  useDisclosure,
} from '@chakra-ui/react';
import axios from 'axios';

export default function AddComment({ formId, fetchComments }) {
  const [text, setText] = useState('');
  const { isOpen, onOpen, onClose } = useDisclosure();
  const url = `http://localhost:8080/api${formId}/comments`;

  const handleSubmit = async () => {
    const data = {
      text,
    };
    const config = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };
    try {
      await axios.post(url, data, config);
      fetchComments();
    } catch (error) {
      console.error(error);
    }
    onClose();
    setText('');
  };

  return (
    <Fragment>
      <Button onClick={onOpen} colorScheme="blue">
        Add Comment
      </Button>
      <Modal onClose={onClose} isOpen={isOpen} isCentered>
        <ModalOverlay>
          <ModalContent>
            <ModalHeader>Add Comment</ModalHeader>
            <ModalCloseButton />
            <ModalBody>
              <FormControl id="text">
                <Textarea
                  type="post-title"
                  value={text}
                  onChange={(e) => setText(e.target.value)}
                />
              </FormControl>
            </ModalBody>
            <ModalFooter>
              <HStack spacing={4}>
                <Button onClick={onClose}>Close</Button>
                <Button
                  onClick={handleSubmit}
                  colorScheme="blue"
                  disabled={!text.trim()}
                >
                  Save
                </Button>
              </HStack>
            </ModalFooter>
          </ModalContent>
        </ModalOverlay>
      </Modal>
    </Fragment>
  );
}
