const Discord = require("discord.js");
const { MessageEmbed } = require("discord.js");
const { Color } = require("../../config.js");

module.exports = {
  name: "kick",
  aliases: [],
  description: "Kick A Member!",
  usage: "Kick <Mention Member>",
  run: async (client, message, args) => {

    message.delete();
    if (!message.member.hasPermission("KICK_MEMBERS"))
      return message.channel.send(
        `You Do Not Have Permission  To Kick Users!`
      );

    let Member = message.mentions.users.first();

    if (!Member)
      return message.channel.send(
        `Error.`
      );

    if (!message.guild.members.cache.get(Member.id))
      return message.channel.send(`Error.`);

    if (Member.id === message.author.id)
      return message.channel.send(`Error.`);

    if (Member.id === client.user.id)
      return message.channel.send(`Error.`);

    if (Member.id === message.guild.owner.user.id)
      return message.channel.send(`Error.`);

    let Reason = args.slice(1).join(" ");

    let User = message.guild.member(Member);

    if (!User.kickable)
      return message.channel.send(`I Can't Kick That Member!`);

    try {
      console.log(`Attempting To Kick Member...`);

      setTimeout(function() {
        User.kick({ reason: `${Reason || "No Reason Provided!"}` });
      }, 2000);
      let embed = new Discord.MessageEmbed()
        .setColor(Color)
        .setTitle(`Member Kicked!`)
        .addField(`Moderator`, `${message.author.tag} (${message.author.id}`)
        .addField(`Kicked Member`, `${Member.tag} (${Member.id})`)
        .addField(`Reason`, `${Reason || "No Reason Provided!"}`)
        .setFooter(`Requested by ${message.author.username}`)
        .setTimestamp();
      if (User && Member.bot === false)
        Member.send(
          `You Have Been Kicked From **${message.guild.name}** For ${Reason ||
            "No Reason Provided!"}`
        );
      message.channel.send(embed);
      console.log(
        `User: ${Member.tag} (${Member.id}) Just Got Kicked From ${
          message.guild.name
        } For ${Reason || "No Reason Provided!"}`
      );
    } catch (error) {
      return message.channel
        .send(
          `Error. I Can Not Kick That Member.`
        )
        .then(() => console.log(error));
    }

    //End
  }
};
