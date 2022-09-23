package com.vasylkorol.ysellb.mapper;

import com.vasylkorol.ysellb.dto.ChatDto;
import com.vasylkorol.ysellb.dto.MessageDto;
import com.vasylkorol.ysellb.model.Chat;
import com.vasylkorol.ysellb.model.Message;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatMapper {
    ChatMapper MAPPER = Mappers.getMapper(ChatMapper.class);
    MessageMapper messageMapper = MessageMapper.MAPPER;

    @Mapping(target = "messages",source = "messageDtoList")
    @Mapping(target = "sender.id",source = "senderId")
    @Mapping(target = "recipient.id",source = "recipientId")
    Chat toChat(ChatDto chatDto);

    @InheritInverseConfiguration
    @Mapping(target = "messageDtoList",source = "messages")
    @Mapping(target = "senderId",source = "sender.id")
    @Mapping(target = "recipientId",source = "recipient.id")
    ChatDto fromChat(Chat chat);

    @Mapping(target = "messages",source = "messageDtoList")
    @Mapping(target = "sender.id",source = "senderId")
    @Mapping(target = "recipient.id",source = "recipientId")
    List<Chat> toChatList(List<ChatDto> chatDtoList);

    @InheritInverseConfiguration
    @Mapping(target = "messageDtoList",source = "messages")
    @Mapping(target = "senderId",source = "sender.id")
    @Mapping(target = "recipientId",source = "recipient.id")
    List<ChatDto> fromChatList(List<Chat> chats);








}
