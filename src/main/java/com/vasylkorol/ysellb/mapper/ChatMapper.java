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

    @InheritInverseConfiguration
    @Mapping(target = "messages",source = "messageDtoList")
    Chat toChat(ChatDto chatDto);

    @Mapping(target = "messageDtoList",source = "messages")
    ChatDto fromChat(Chat chat);
    List<Message> toMessageList(List<MessageDto> messageDtoList);



}
