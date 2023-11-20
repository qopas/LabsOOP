from discord import Intents
from discord.ext import commands

# Define intents
intents = Intents.default()
intents.message_content = True

bot = commands.Bot(command_prefix="!", intents=intents)

# Event triggered when the bot is ready
@bot.event
async def on_ready():
    print(f'We have logged in as {bot.user.name}')


# Command to perform arithmetic operations
@bot.command(name='calculate', help='Perform arithmetic operations. Usage: !calculate 2+2')
async def calculate(ctx, *, expression):
    try:
        result = eval(expression)
        await ctx.send(f'The result of {expression} is: {result}')
    except Exception as e:
        await ctx.send(f'Error: {str(e)}')


@bot.command(name='ping', help='Check if the bot is responsive')
async def ping(ctx):
    await ctx.send('Pong!')

@bot.event
async def on_message(message):
    print(f'Message received: {message.content}')
    await bot.process_commands(message)


# Run the bot with the token
bot.run('MTE3NDY2NjY0Mzg0NzI1NDAzNg.GJkCOd.3VSMexMlB8OSlt8mNxrNmt_fIwK97hguKXxBEA')
