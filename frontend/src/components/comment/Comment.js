import React from 'react';

import { Flex, Spacer, Box, HStack, Text } from '@chakra-ui/react';

import EditComment from './EditComment';
import DeleteComment from './DeleteComment';
import { isUser, isSameUser } from '../../utils/checkUser';

export default function Comment({ comment, fetchComments }) {
  return (
    <HStack key={comment.id} w="100%" alignItems="flex-start">
      <Box bg="gray.100" p={4} rounded="md" w="100%">
        <Text>{comment.text}</Text>
        <Flex>
          <Text>{comment.user.username}</Text>
          {isUser() && isSameUser(comment.user.id) && <Spacer />}
          {isUser() && isSameUser(comment.user.id) && (
            <EditComment comment={comment} fetchComments={fetchComments} />
          )}
          {isUser() && isSameUser(comment.user.id) && (
            <DeleteComment comment={comment} fetchComments={fetchComments} />
          )}
        </Flex>
      </Box>
    </HStack>
  );
}
